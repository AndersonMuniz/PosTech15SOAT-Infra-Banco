package br.com.fiap.numberone.client.application.mappers;

import br.com.fiap.numberone.client.api.dtos.requests.ClientRequest;
import br.com.fiap.numberone.client.api.dtos.responses.ClientResponse;
import br.com.fiap.numberone.client.domain.entities.Client;
import br.com.fiap.numberone.client.domain.entities.Cliente;
import br.com.fiap.numberone.client.domain.enums.TipoDocumento;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ClientMapperTest {

    private final ClientMapper mapper = new ClientMapper();

    @Test
    void deveMapearRequestParaEntityComAtivoPadraoTrue() {
        ClientRequest request = new ClientRequest("Ana", TipoDocumento.PESSOA_FISICA, "52998224725",
                "11999999999", "Rua 1", null);

        Client cliente = mapper.toEntity(request);

        assertEquals("Ana", cliente.getNome());
        assertEquals(TipoDocumento.PESSOA_FISICA, cliente.getTipoDocumento());
        assertTrue(cliente.getAtivo());
    }

    @Test
    void deveMapearRequestParaEntityMantendoAtivoInformado() {
        ClientRequest request = new ClientRequest("Empresa", TipoDocumento.PESSOA_JURIDICA, "11444777000161",
                "1133333333", "Av 2", false);

        Client client = mapper.toEntity(request);

        assertFalse(client.getAtivo());
    }

    @Test
    void deveMapearEntityParaResponse() {
        UUID id = UUID.randomUUID();
        LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
        LocalDateTime updatedAt = LocalDateTime.now();

        Client client = Client.builder()
                .id(id)
                .nome("Carlos")
                .tipoDocumento(TipoDocumento.PESSOA_FISICA)
                .documento("52998224725")
                .telefone("11912345678")
                .endereco("Rua C")
                .ativo(true)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();

        ClientResponse response = mapper.toResponse(client);

        assertEquals(id, response.id());
        assertEquals("Carlos", response.nome());
        assertEquals(createdAt, response.createdAt());
        assertEquals(updatedAt, response.updatedAt());
    }
}
