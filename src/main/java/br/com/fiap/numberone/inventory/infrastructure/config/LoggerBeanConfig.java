package br.com.fiap.numberone.inventory.infrastructure.config;

import br.com.fiap.numberone.inventory.application.gateways.LoggerGateway;
import br.com.fiap.numberone.shared.infrastructure.logging.Slf4jLoggerGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerBeanConfig {


    @Bean
    public LoggerGateway loggerGateway() {
        return new Slf4jLoggerGateway();
    }
}
