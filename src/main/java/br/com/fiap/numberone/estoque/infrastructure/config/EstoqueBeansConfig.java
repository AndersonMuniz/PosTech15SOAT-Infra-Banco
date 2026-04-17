package br.com.fiap.numberone.estoque.infrastructure.config;

import br.com.fiap.numberone.estoque.application.gateways.LoggerGateway;
import br.com.fiap.numberone.estoque.application.gateways.ServicoGateway;
import br.com.fiap.numberone.estoque.application.services.ServicoService;
import br.com.fiap.numberone.estoque.infrastructure.persistence.gateways.ServicoGatewayImpl;
import br.com.fiap.numberone.estoque.infrastructure.persistence.mappers.ServicoMapper;
import br.com.fiap.numberone.estoque.infrastructure.persistence.repositories.ServicoRepository;
import br.com.fiap.numberone.shared.infrastructure.logging.Slf4jLoggerGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração de beans para o módulo de Estoque.
 * Centraliza a criação de beans do application layer, mantendo-o livre de frameworks.
 */
@Configuration
public class EstoqueBeansConfig {

    /**
     * Cria o gateway de Servico como bean gerenciado pelo Spring.
     */
    @Bean
    public ServicoGateway servicoGateway(ServicoRepository repository, ServicoMapper mapper) {
        return new ServicoGatewayImpl(repository, mapper);
    }

    @Bean
    public LoggerGateway loggerGateway() {
        return new Slf4jLoggerGateway();
    }

    /**
     * Cria a service de Servico como bean gerenciado pelo Spring.
     */
    @Bean
    public ServicoService servicoService(ServicoGateway servicoGateway, LoggerGateway loggerGateway) {
        return new ServicoService(servicoGateway,loggerGateway);
    }
}

