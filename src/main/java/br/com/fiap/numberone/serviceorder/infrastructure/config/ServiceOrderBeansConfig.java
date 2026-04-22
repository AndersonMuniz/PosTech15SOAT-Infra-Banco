package br.com.fiap.numberone.serviceorder.infrastructure.config;

import br.com.fiap.numberone.serviceorder.application.gateways.CustomerGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderBudgetApprovalNotificationGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderBudgetGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderItemGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.VehicleGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.AutomotiveServiceGateway;
import br.com.fiap.numberone.serviceorder.application.services.ServiceOrderBudgetService;
import br.com.fiap.numberone.serviceorder.application.services.ServiceOrderItemService;
import br.com.fiap.numberone.serviceorder.application.services.ServiceOrderService;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.gateways.ServiceOrderBudgetGatewayImpl;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.gateways.ServiceOrderItemGatewayImpl;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.gateways.ServiceOrderGatewayImpl;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.mappers.ServiceOrderBudgetMapper;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.mappers.ServiceOrderItemMapper;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.mappers.ServiceOrderMapper;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.repositories.ServiceOrderBudgetRepository;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.repositories.ServiceOrderItemRepository;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.repositories.ServiceOrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceOrderBeansConfig {

    @Bean
    public ServiceOrderGateway serviceOrderGateway(ServiceOrderRepository serviceOrderRepository, ServiceOrderMapper serviceOrderMapper) {
        return new ServiceOrderGatewayImpl(serviceOrderRepository, serviceOrderMapper);
    }

    @Bean
    public ServiceOrderItemGateway serviceOrderItemGateway(ServiceOrderItemRepository serviceOrderItemRepository, ServiceOrderItemMapper serviceOrderItemMapper) {
        return new ServiceOrderItemGatewayImpl(serviceOrderItemRepository, serviceOrderItemMapper);
    }

    @Bean
    public ServiceOrderBudgetGateway serviceOrderBudgetGateway(ServiceOrderBudgetRepository serviceOrderBudgetRepository, ServiceOrderBudgetMapper serviceOrderBudgetMapper) {
        return new ServiceOrderBudgetGatewayImpl(serviceOrderBudgetRepository, serviceOrderBudgetMapper);
    }

    @Bean
    public ServiceOrderService serviceOrderService(
            ServiceOrderGateway serviceOrderGateway,
            CustomerGateway customerGateway,
            VehicleGateway vehicleGateway
    ) {
        return new ServiceOrderService(
                serviceOrderGateway,
                customerGateway,
                vehicleGateway
        );
    }

    @Bean
    public ServiceOrderItemService serviceOrderItemService(
            ServiceOrderGateway serviceOrderGateway,
            ServiceOrderItemGateway serviceOrderItemGateway,
            AutomotiveServiceGateway automotiveServiceGateway
    ) {
        return new ServiceOrderItemService(serviceOrderGateway, serviceOrderItemGateway, automotiveServiceGateway);
    }

    @Bean
    public ServiceOrderBudgetService serviceOrderBudgetService(
            ServiceOrderGateway serviceOrderGateway,
            ServiceOrderBudgetGateway serviceOrderBudgetGateway,
            ServiceOrderBudgetApprovalNotificationGateway serviceOrderBudgetApprovalNotificationGateway
    ) {
        return new ServiceOrderBudgetService(
                serviceOrderGateway,
                serviceOrderBudgetGateway,
                serviceOrderBudgetApprovalNotificationGateway
        );
    }

}
