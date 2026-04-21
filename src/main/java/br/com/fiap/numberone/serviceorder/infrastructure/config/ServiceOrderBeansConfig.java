package br.com.fiap.numberone.serviceorder.infrastructure.config;

import br.com.fiap.numberone.serviceorder.application.gateways.CustomerGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderApprovalNotificationGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.ServiceOrderItemGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.VehicleGateway;
import br.com.fiap.numberone.serviceorder.application.gateways.AutomotiveServiceGateway;
import br.com.fiap.numberone.serviceorder.application.services.ServiceOrderItemService;
import br.com.fiap.numberone.serviceorder.application.services.ServiceOrderService;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.gateways.ServiceOrderItemGatewayImpl;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.gateways.ServiceOrderGatewayImpl;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.mappers.ServiceOrderItemMapper;
import br.com.fiap.numberone.serviceorder.infrastructure.persistence.mappers.ServiceOrderMapper;
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
    public ServiceOrderService serviceOrderService(
            ServiceOrderGateway serviceOrderGateway,
            ServiceOrderApprovalNotificationGateway serviceOrderApprovalNotificationGateway,
            CustomerGateway customerGateway,
            VehicleGateway vehicleGateway
    ) {
        return new ServiceOrderService(
                serviceOrderGateway,
                serviceOrderApprovalNotificationGateway,
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

}
