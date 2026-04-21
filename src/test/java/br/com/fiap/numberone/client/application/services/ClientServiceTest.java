package br.com.fiap.numberone.client.application.services;

import br.com.fiap.numberone.client.api.dtos.requests.ClientRequest;
import br.com.fiap.numberone.client.api.dtos.responses.ClientResponse;
import br.com.fiap.numberone.client.application.mappers.ClientMapper;
import br.com.fiap.numberone.client.domain.entities.Cliente;
import br.com.fiap.numberone.client.domain.enums.TipoDocumento;
import br.com.fiap.numberone.client.infrastructure.persistence.entities.ClienteEntity;
import br.com.fiap.numberone.client.infrastructure.persistence.mappers.ClienteEntityMapper;
import br.com.fiap.numberone.client.infrastructure.repositories.ClienteRepository;
import br.com.fiap.numberone.shared.api.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    private ClientService clientService;

    @BeforeEach
    void setUp() {
        clientService = new ClientService(clienteRepository, new ClientMapper(), new ClienteEntityMapper());
    }

    @Test
    void deveCriarCliente() {
        ClientRequest request = new ClientRequest("Ana", TipoDocumento.PESSOA_FISICA, "52998224725",
                "11999999999", "Rua A", null);

        UUID id = UUID.randomUUID();
        ClienteEntity saved = ClienteEntity.builder()
                .id(id)
                .nome("Ana")
                .tipoDocumento(TipoDocumento.PESSOA_FISICA)
                .documento("52998224725")
                .telefone("11999999999")
                .endereco("Rua A")
                .ativo(true)
                .createdAt(LocalDateTime.now())
                .build();

        when(clienteRepository.save(any())).thenReturn(saved);

        ClientResponse response = clientService.create(request);

        assertEquals(id, response.id());
        assertEquals("Ana", response.nome());
        verify(clienteRepository).save(any());
    }

    @Test
    void deveAtualizarCliente() {
        UUID id = UUID.randomUUID();
        ClientRequest request = new ClientRequest("Novo Nome", TipoDocumento.PESSOA_FISICA, "52998224725",
                "11999999999", "Rua B", true);

        ClienteEntity existente = ClienteEntity.builder()
                .id(id)
                .nome("Antigo")
                .tipoDocumento(TipoDocumento.PESSOA_FISICA)
                .documento("52998224725")
                .telefone("11888888888")
                .endereco("Rua A")
                .ativo(true)
                .createdAt(LocalDateTime.now().minusDays(1))
                .build();

        ClienteEntity atualizado = ClienteEntity.builder()
                .id(id)
                .nome("Novo Nome")
                .tipoDocumento(TipoDocumento.PESSOA_FISICA)
                .documento("52998224725")
                .telefone("11999999999")
                .endereco("Rua B")
                .ativo(true)
                .createdAt(existente.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();

        when(clienteRepository.findById(id)).thenReturn(Optional.of(existente));
        when(clienteRepository.save(any())).thenReturn(atualizado);

        ClientResponse response = clientService.update(id, request);

        assertEquals("Novo Nome", response.nome());
        verify(clienteRepository).findById(id);
        verify(clienteRepository).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoAtualizarClienteInexistente() {
        UUID id = UUID.randomUUID();
        ClientRequest request = new ClientRequest("Nome", TipoDocumento.PESSOA_FISICA, "52998224725",
                "11999999999", "Rua", true);

        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clientService.update(id, request));
    }

    @Test
    void deveBuscarClientePorId() {
        UUID id = UUID.randomUUID();
        ClienteEntity entity = ClienteEntity.builder()
                .id(id)
                .nome("Ana")
                .tipoDocumento(TipoDocumento.PESSOA_FISICA)
                .documento("52998224725")
                .telefone("11999999999")
                .endereco("Rua")
                .ativo(true)
                .build();

        when(clienteRepository.findById(id)).thenReturn(Optional.of(entity));

        ClientResponse response = clientService.findById(id);

        assertEquals(id, response.id());
        verify(clienteRepository).findById(id);
    }

    @Test
    void deveLancarExcecaoQuandoBuscarClienteInexistentePorId() {
        UUID id = UUID.randomUUID();
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clientService.findById(id));
    }

    @Test
    void deveListarTodosOsClientes() {
        ClienteEntity c1 = ClienteEntity.builder()
                .id(UUID.randomUUID())
                .nome("A")
                .tipoDocumento(TipoDocumento.PESSOA_FISICA)
                .documento("52998224725")
                .telefone("1")
                .endereco("X")
                .ativo(true)
                .build();
        ClienteEntity c2 = ClienteEntity.builder()
                .id(UUID.randomUUID())
                .nome("B")
                .tipoDocumento(TipoDocumento.PESSOA_JURIDICA)
                .documento("11444777000161")
                .telefone("2")
                .endereco("Y")
                .ativo(false)
                .build();

        when(clienteRepository.findAll()).thenReturn(List.of(c1, c2));

        List<ClientResponse> responses = clientService.findAll();

        assertEquals(2, responses.size());
        verify(clienteRepository).findAll();
    }

    @Test
    void deveRemoverCliente() {
        UUID id = UUID.randomUUID();
        ClienteEntity entity = ClienteEntity.builder().id(id).build();

        when(clienteRepository.findById(id)).thenReturn(Optional.of(entity));

        clientService.delete(id);

        verify(clienteRepository).delete(entity);
    }

    @Test
    void deveLancarExcecaoQuandoRemoverClienteInexistente() {
        UUID id = UUID.randomUUID();
        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clientService.delete(id));
    }
}
