package br.com.fiap.numberone.inventory.infrastructure.config;

import br.com.fiap.numberone.inventory.application.gateways.AutoServiceGateway;
import br.com.fiap.numberone.inventory.application.gateways.LoggerGateway;
import br.com.fiap.numberone.inventory.application.services.AutomotiveServiceService;
import br.com.fiap.numberone.inventory.infrastructure.persistence.gateways.AutoServiceGatewayImpl;
import br.com.fiap.numberone.inventory.infrastructure.persistence.mappers.AutomotiveServicePersistenceMapper;
import br.com.fiap.numberone.inventory.infrastructure.persistence.repositories.AutoServiceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutoServiceBeansConfig {

    @Bean
    public AutoServiceGateway autoServiceGateway(AutoServiceRepository autoServiceRepository, AutomotiveServicePersistenceMapper autoServiceEntityMapper) {
        return new AutoServiceGatewayImpl(autoServiceRepository, autoServiceEntityMapper);
    }

    @Bean
    public AutomotiveServiceService autoServiceService(AutoServiceGateway autoServiceGateway, LoggerGateway loggerGateway) {
        return new AutomotiveServiceService(autoServiceGateway,loggerGateway);
    }
}

