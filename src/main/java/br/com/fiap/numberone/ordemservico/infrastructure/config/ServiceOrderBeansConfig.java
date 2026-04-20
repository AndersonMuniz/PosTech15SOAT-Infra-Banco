package br.com.fiap.numberone.ordemservico.infrastructure.config;

import br.com.fiap.numberone.ordemservico.application.gateways.CustomerGateway;
import br.com.fiap.numberone.ordemservico.application.gateways.ServiceOrderGateway;
import br.com.fiap.numberone.ordemservico.application.gateways.VehicleGateway;
import br.com.fiap.numberone.ordemservico.application.services.ServiceOrderService;
import br.com.fiap.numberone.ordemservico.infrastructure.persistence.gateways.ServiceOrderGatewayImpl;
import br.com.fiap.numberone.ordemservico.infrastructure.persistence.mappers.ServiceOrderMapper;
import br.com.fiap.numberone.ordemservico.infrastructure.persistence.repositories.ServiceOrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceOrderBeansConfig {

    @Bean
    public ServiceOrderGateway serviceOrderGateway(ServiceOrderRepository serviceOrderRepository, ServiceOrderMapper serviceOrderMapper) {
        return new ServiceOrderGatewayImpl(serviceOrderRepository, serviceOrderMapper);
    }

    @Bean
    public ServiceOrderService serviceOrderService(ServiceOrderGateway serviceOrderGateway, CustomerGateway customerGateway, VehicleGateway vehicleGateway) {
        return new ServiceOrderService(serviceOrderGateway, customerGateway, vehicleGateway);
    }

}
