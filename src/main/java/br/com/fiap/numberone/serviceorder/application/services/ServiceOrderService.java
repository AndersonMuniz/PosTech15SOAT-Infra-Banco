package br.com.fiap.numberone.serviceorder.application.services;

import br.com.fiap.numberone.serviceorder.application.commands.ServiceOrderDeliveryUpdate;
import br.com.fiap.numberone.serviceorder.application.commands.ServiceOrderFinalDiagnosisUpdate;
import br.com.fiap.numberone.serviceorder.application.gateways.AutomotiveServiceGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.CustomerGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.InventoryItemGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.VehicleGateway;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderItem;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrderItemSupply;
import br.com.fiap.numberone.serviceorder.domain.enums.OrderItemStatus;
import br.com.fiap.numberone.serviceorder.domain.enums.ServiceOrderStatus;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.Diagnosis;
import br.com.fiap.numberone.serviceorder.domain.entities.ServiceOrder;
import br.com.fiap.numberone.serviceorder.domain.references.AutomotiveService;
import br.com.fiap.numberone.serviceorder.domain.references.Customer;
import br.com.fiap.numberone.serviceorder.domain.references.InventoryItem;
import br.com.fiap.numberone.serviceorder.domain.references.Vehicle;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.ServiceOrderAverageExecutionTime;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.ServiceOrderEstimatedTime;
import br.com.fiap.numberone.serviceorder.domain.valueobjects.ServiceOrderValue;
import br.com.fiap.numberone.shared.api.exception.ResourceNotFoundException;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ServiceOrderService {

    private final ServiceOrderGateway serviceOrderGateway;
    private final CustomerGateway customerGateway;
    private final VehicleGateway vehicleGateway;
    private final AutomotiveServiceGateway automotiveServiceGateway;
    private final InventoryItemGateway inventoryItemGateway;

    public ServiceOrderService(
            ServiceOrderGateway serviceOrderGateway,
            CustomerGateway customerGateway,
            VehicleGateway vehicleGateway,
            AutomotiveServiceGateway automotiveServiceGateway,
            InventoryItemGateway inventoryItemGateway
    ) {
        this.serviceOrderGateway = serviceOrderGateway;
        this.customerGateway = customerGateway;
        this.vehicleGateway = vehicleGateway;
        this.automotiveServiceGateway = automotiveServiceGateway;
        this.inventoryItemGateway = inventoryItemGateway;
    }

    public List<ServiceOrder> getServiceOrders() {
        return serviceOrderGateway.findAll()
                .stream()
                .filter(this::isVisibleInServiceOrderList)
                .sorted(Comparator
                        .comparingInt((ServiceOrder serviceOrder) -> statusPriority(serviceOrder.getStatus()))
                        .thenComparing(ServiceOrder::getEntryDateTime, Comparator.nullsLast(Comparator.naturalOrder()))
                        .thenComparing(ServiceOrder::getCreatedAt, Comparator.nullsLast(Comparator.naturalOrder())))
                .toList();
    }

    public ServiceOrder createServiceOrder(ServiceOrder serviceOrder) {
        Customer validatedCustomer = resolveCustomer(serviceOrder.getCustomer());
        Vehicle validatedVehicle = resolveVehicle(serviceOrder.getVehicle(), validatedCustomer);

        serviceOrder.attachCustomer(validatedCustomer);
        serviceOrder.attachVehicle(validatedVehicle);
        serviceOrder.updateStatus(ServiceOrderStatus.RECEIVED);
        attachServiceItems(serviceOrder);

        return serviceOrderGateway.save(serviceOrder);
    }

    public ServiceOrder addFinalDiagnosis(UUID id, Diagnosis diagnosis) {
        ServiceOrder serviceOrder = getServiceOrder(id);

        serviceOrder.applyFinalDiagnosis(diagnosis.getFinalDiagnosisDescription(), diagnosis.getNotes());
        serviceOrder.defineExpectedDateTime(diagnosis.getExpectedDateTime());
        serviceOrder.updateStatus(ServiceOrderStatus.IN_DIAGNOSIS);

        return serviceOrderGateway.updateFinalDiagnosis(
                ServiceOrderFinalDiagnosisUpdate.builder()
                        .serviceOrderId(serviceOrder.getId())
                        .finalDiagnosisDescription(serviceOrder.getFinalDiagnosisDescription())
                        .notes(serviceOrder.getNotes())
                        .expectedDateTime(serviceOrder.getExpectedDateTime())
                        .status(serviceOrder.getStatus())
                        .build()
        );
    }

    public ServiceOrder startOrderService(UUID id) {
        ServiceOrder serviceOrder = getServiceOrder(id);

        return changeOrderStatus(serviceOrder, ServiceOrderStatus.IN_PROGRESS);
    }

    public ServiceOrder cancelOrderService(UUID id) {
        ServiceOrder serviceOrder = getServiceOrder(id);

        return changeOrderStatus(serviceOrder, ServiceOrderStatus.CANCELLED);
    }

    public ServiceOrder completeOrderService(UUID id) {
        ServiceOrder serviceOrder = getServiceOrder(id);

        serviceOrder.validateServiceItemsAreFinished();
        return changeOrderStatus(serviceOrder, ServiceOrderStatus.COMPLETED);
    }


    public ServiceOrder deliverOrderService(UUID id) {
        ServiceOrder serviceOrder = getServiceOrder(id);

        if (serviceOrder.getStatus() == ServiceOrderStatus.COMPLETED) {
            serviceOrder.validateServiceItemsAreFinished();
        }

        serviceOrder.updateStatus(ServiceOrderStatus.DELIVERED);

        return serviceOrderGateway.deliver(
                ServiceOrderDeliveryUpdate.builder()
                        .serviceOrderId(serviceOrder.getId())
                        .deliveryDateTime(LocalDateTime.now())
                        .status(serviceOrder.getStatus())
                        .build()
        );
    }

    public ServiceOrderValue calculateServices(UUID id) {
        ServiceOrder serviceOrder = getServiceOrder(id);

        BigDecimal totalValue = serviceOrder.getServiceItemsTotalValue();

        return ServiceOrderValue.builder()
                .serviceOrderId(id)
                .totalValue(totalValue)
                .build();
    }

    public ServiceOrderEstimatedTime calculateEstimatedTime(UUID id) {
        ServiceOrder serviceOrder = getServiceOrder(id);

        int totalEstimatedMinutes = serviceOrder.getServiceItems()
                .stream()
                .filter(serviceOrderItem -> serviceOrderItem.getStatus() != OrderItemStatus.CANCELLED)
                .map(ServiceOrderItem::getAutomotiveService)
                .filter(Objects::nonNull)
                .map(AutomotiveService::getEstimatedTimeMinutes)
                .filter(Objects::nonNull)
                .reduce(0, Integer::sum);

        return ServiceOrderEstimatedTime.builder()
                .serviceOrderId(id)
                .totalEstimatedMinutes(totalEstimatedMinutes)
                .suggestedExpectedDateTime(LocalDateTime.now().plusMinutes(totalEstimatedMinutes))
                .build();
    }

    public ServiceOrderAverageExecutionTime calculateAverageServiceExecutionTime(UUID id) {
        ServiceOrder serviceOrder = getServiceOrder(id);

        List<ServiceOrderItem> items = serviceOrder.getServiceItems();

        int completedServices = countServicesByStatus(items, OrderItemStatus.COMPLETED);
        int pendingServices = countServicesByStatus(items, OrderItemStatus.PENDING);
        int inProgressServices = countServicesByStatus(items, OrderItemStatus.IN_PROGRESS);
        int cancelledServices = countServicesByStatus(items, OrderItemStatus.CANCELLED);
        int waitingServices = countServicesByStatus(items, OrderItemStatus.WAITING_FOR_PARTS_AND_SUPPLIES);

        long averageExecutionMinutes = (long) items.stream()
                .filter(item -> item.getStatus() == OrderItemStatus.COMPLETED)
                .filter(item -> item.getStartDateTime() != null && item.getEndDateTime() != null)
                .mapToLong(item -> Duration.between(item.getStartDateTime(), item.getEndDateTime()).toMinutes())
                .average()
                .orElse(0);

        return ServiceOrderAverageExecutionTime.builder()
                .serviceOrderId(serviceOrder.getId())
                .completedServices(completedServices)
                .pendingServices(pendingServices)
                .inProgressServices(inProgressServices)
                .cancelledServices(cancelledServices)
                .waitingServices(waitingServices)
                .averageExecutionMinutes(averageExecutionMinutes)
                .build();
    }

    private static int countServicesByStatus(List<ServiceOrderItem> items, OrderItemStatus completed) {
        return (int) items.stream()
                .filter(item -> item.getStatus() == completed)
                .count();
    }


    public ServiceOrder getServiceOrder(UUID id) {
        return serviceOrderGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Service order not found for id: " + id));
    }

    private ServiceOrder changeOrderStatus(ServiceOrder serviceOrder, ServiceOrderStatus targetStatus) {
        serviceOrder.updateStatus(targetStatus);
        return serviceOrderGateway.updateStatus(serviceOrder.getId(), serviceOrder.getStatus());
    }

    private void attachServiceItems(ServiceOrder serviceOrder) {
        if (serviceOrder.getServiceItems() == null) {
            return;
        }

        List<ServiceOrderItem> resolvedItems = serviceOrder.getServiceItems()
                .stream()
                .map(serviceOrderItem -> resolveServiceOrderItem(serviceOrder, serviceOrderItem))
                .toList();
        serviceOrder.replaceServiceItems(resolvedItems);
    }

    private ServiceOrderItem resolveServiceOrderItem(ServiceOrder serviceOrder, ServiceOrderItem serviceOrderItem) {
        AutomotiveService automotiveService = resolveAutomotiveService(serviceOrderItem.getAutomotiveService());
        BigDecimal value = Objects.requireNonNullElse(serviceOrderItem.getValue(), automotiveService.getBaseValue());
        ServiceOrderItem resolvedItem = ServiceOrderItem.builder()
                .id(serviceOrderItem.getId())
                .serviceOrder(serviceOrder)
                .automotiveService(automotiveService)
                .value(value)
                .optional(serviceOrderItem.getOptional())
                .supplies(resolveSupplies(serviceOrderItem))
                .build();

        resolvedItem.attachServiceOrder(serviceOrder);
        resolvedItem.attachAutomotiveService(automotiveService);
        resolvedItem.updateStatus(OrderItemStatus.PENDING);
        resolvedItem.getSupplies().forEach(supply -> supply.attachServiceOrderItem(resolvedItem));
        return resolvedItem;
    }

    private List<ServiceOrderItemSupply> resolveSupplies(ServiceOrderItem serviceOrderItem) {
        if (serviceOrderItem.getSupplies() == null) {
            return List.of();
        }

        return serviceOrderItem.getSupplies()
                .stream()
                .map(this::resolveSupply)
                .toList();
    }

    private ServiceOrderItemSupply resolveSupply(ServiceOrderItemSupply supply) {
        InventoryItem inventoryItem = resolveInventoryItem(supply.getInventoryItem());
        ServiceOrderItemSupply resolvedSupply = ServiceOrderItemSupply.builder()
                .id(supply.getId())
                .inventoryItem(inventoryItem)
                .quantityUsed(supply.getQuantityUsed())
                .build();
        resolvedSupply.attachInventoryItem(inventoryItem);
        return resolvedSupply;
    }

    private Customer resolveCustomer(Customer customer) {
        if (customer.getId() != null) {
            return customerGateway.findById(customer.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Cliente nao encontrado"));
        }

        requireText(customer.getDocument(), "documento do cliente e obrigatorio para criar cliente");
        requireNonNull(customer.getDocumentType(), "tipoDocumento do cliente e obrigatorio para criar cliente");
        return customerGateway.findOrCreateByDocument(Customer.builder()
                .name(requireText(customer.getName(), "nome do cliente e obrigatorio para criar cliente"))
                .documentType(customer.getDocumentType())
                .document(customer.getDocument())
                .email(requireText(customer.getEmail(), "email do cliente e obrigatorio para criar cliente"))
                .phone(requireText(customer.getPhone(), "telefone do cliente e obrigatorio para criar cliente"))
                .address(requireText(customer.getAddress(), "endereco do cliente e obrigatorio para criar cliente"))
                .active(customer.getActive() == null ? Boolean.TRUE : customer.getActive())
                .build());
    }

    private Vehicle resolveVehicle(Vehicle vehicle, Customer customer) {
        if (vehicle.getId() != null) {
            return vehicleGateway.findById(vehicle.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found"));
        }

        String normalizedLicensePlate = normalize(requireText(vehicle.getLicensePlate(), "placa do veiculo e obrigatoria para criar veiculo"));
        return vehicleGateway.findByLicensePlate(normalizedLicensePlate)
                .orElseGet(() -> vehicleGateway.save(Vehicle.builder()
                        .licensePlate(normalizedLicensePlate)
                        .brand(requireText(vehicle.getBrand(), "marca do veiculo e obrigatoria para criar veiculo"))
                        .model(requireText(vehicle.getModel(), "modelo do veiculo e obrigatorio para criar veiculo"))
                        .year(requireNonNull(vehicle.getYear(), "ano do veiculo e obrigatorio para criar veiculo"))
                        .customerId(customer.getId())
                        .build()));
    }

    private AutomotiveService resolveAutomotiveService(AutomotiveService automotiveService) {
        if (automotiveService.getId() != null) {
            return automotiveServiceGateway.findById(automotiveService.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Automotive service not found for id: " + automotiveService.getId()));
        }

        String code = normalize(requireText(automotiveService.getCode(), "codigo do servico e obrigatorio para criar servico"));
        return automotiveServiceGateway.findByCode(code)
                .orElseGet(() -> automotiveServiceGateway.save(AutomotiveService.builder()
                        .id(UUID.randomUUID())
                        .code(code)
                        .name(requireText(automotiveService.getName(), "nome do servico e obrigatorio para criar servico"))
                        .description(requireText(automotiveService.getDescription(), "descricao do servico e obrigatoria para criar servico"))
                        .serviceType(requireText(automotiveService.getServiceType(), "tipoServico e obrigatorio para criar servico"))
                        .baseValue(requireNonNull(automotiveService.getBaseValue(), "valorBase do servico e obrigatorio para criar servico"))
                        .estimatedTimeMinutes(requireNonNull(automotiveService.getEstimatedTimeMinutes(), "tempoEstimadoMinutos e obrigatorio para criar servico"))
                        .active(automotiveService.getActive() == null ? Boolean.TRUE : automotiveService.getActive())
                        .build()));
    }

    private InventoryItem resolveInventoryItem(InventoryItem inventoryItem) {
        if (inventoryItem.getId() != null) {
            return inventoryItemGateway.findById(inventoryItem.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Inventory item not found for id: " + inventoryItem.getId()));
        }

        String code = normalize(requireText(inventoryItem.getCode(), "codigo da peca e obrigatorio para criar peca"));
        return inventoryItemGateway.findByCode(code)
                .orElseGet(() -> inventoryItemGateway.save(InventoryItem.builder()
                        .id(UUID.randomUUID())
                        .code(code)
                        .name(requireText(inventoryItem.getName(), "nome da peca e obrigatorio para criar peca"))
                        .description(inventoryItem.getDescription())
                        .itemType(requireNonNull(inventoryItem.getItemType(), "tipoItem da peca e obrigatorio para criar peca"))
                        .unitOfMeasure(requireNonNull(inventoryItem.getUnitOfMeasure(), "unidadeMedida da peca e obrigatoria para criar peca"))
                        .costPerUnit(requireNonNull(inventoryItem.getCostPerUnit(), "custoUnitario da peca e obrigatorio para criar peca"))
                        .salePrice(requireNonNull(inventoryItem.getSalePrice(), "precoVenda da peca e obrigatorio para criar peca"))
                        .inventoryQuantity(requireNonNull(inventoryItem.getInventoryQuantity(), "quantidadeEstoque da peca e obrigatoria para criar peca"))
                        .minimumInventoryQuantity(requireNonNull(inventoryItem.getMinimumInventoryQuantity(), "estoqueMinimo da peca e obrigatorio para criar peca"))
                        .brand(inventoryItem.getBrand())
                        .applicableVehicle(inventoryItem.getApplicableVehicle())
                        .active(inventoryItem.getActive() == null ? Boolean.TRUE : inventoryItem.getActive())
                        .build()));
    }

    private String requireText(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    private <T> T requireNonNull(T value, String message) {
        if (value == null) {
            throw new IllegalArgumentException(message);
        }
        return value;
    }

    private String normalize(String value) {
        return value.trim().toUpperCase();
    }

    private boolean isVisibleInServiceOrderList(ServiceOrder serviceOrder) {
        return !List.of(ServiceOrderStatus.COMPLETED, ServiceOrderStatus.DELIVERED)
                .contains(serviceOrder.getStatus());
    }

    private int statusPriority(ServiceOrderStatus status) {
        if (status == null) {
            return 99;
        }

        return switch (status) {
            case IN_PROGRESS -> 0;
            case WAITING_APPROVAL -> 1;
            case IN_DIAGNOSIS -> 2;
            case RECEIVED -> 3;
            default -> 99;
        };
    }

}
