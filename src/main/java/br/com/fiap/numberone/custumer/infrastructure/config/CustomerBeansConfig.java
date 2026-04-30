package br.com.fiap.numberone.custumer.infrastructure.config;

import br.com.fiap.numberone.custumer.api.mappers.CustomerApiMapper;
import br.com.fiap.numberone.custumer.application.gateways.CustomerGateway;
import br.com.fiap.numberone.custumer.application.services.CustomerService;
import br.com.fiap.numberone.custumer.infrastructure.persistence.gateways.CustomerGatewayImpl;
import br.com.fiap.numberone.custumer.infrastructure.persistence.mappers.CustomerEntityMapper;
import br.com.fiap.numberone.custumer.infrastructure.persistence.repositories.CustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerBeansConfig {

    @Bean
    public CustomerGateway customerGateway(
            CustomerRepository customerRepository,
            CustomerEntityMapper customerEntityMapper
    ) {
        return new CustomerGatewayImpl(customerRepository, customerEntityMapper);
    }

    @Bean
    public CustomerService customerService(CustomerGateway customerGateway) {
        return new CustomerService(customerGateway);
    }

    @Bean
    public CustomerApiMapper customerApiMapper() {
        return new CustomerApiMapper();
    }
}


