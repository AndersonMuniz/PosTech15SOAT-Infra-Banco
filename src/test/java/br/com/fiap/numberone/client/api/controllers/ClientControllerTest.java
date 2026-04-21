package br.com.fiap.numberone.client.api.controllers;

import br.com.fiap.numberone.client.api.dtos.requests.ClientRequest;
import br.com.fiap.numberone.client.api.dtos.responses.ClientResponse;
import br.com.fiap.numberone.client.application.services.ClientService;
import br.com.fiap.numberone.client.domain.enums.TipoDocumento;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
@AutoConfigureMockMvc(addFilters = false)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClientService clientService;

    @Test
    void deveCriarCliente() throws Exception {
        UUID id = UUID.randomUUID();
        ClientRequest request = new ClientRequest("Ana", TipoDocumento.PESSOA_FISICA, "52998224725",
                "11999999999", "Rua A", true);
        ClientResponse response = new ClientResponse(id, "Ana", TipoDocumento.PESSOA_FISICA, "52998224725",
                "11999999999", "Rua A", true, LocalDateTime.now(), null);

        when(clientService.create(any(ClientRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", org.hamcrest.Matchers.containsString("/api/clientes/" + id)))
                .andExpect(jsonPath("$.id").value(id.toString()));
    }

    @Test
    void deveAtualizarCliente() throws Exception {
        UUID id = UUID.randomUUID();
        ClientRequest request = new ClientRequest("Ana", TipoDocumento.PESSOA_FISICA, "52998224725",
                "11999999999", "Rua A", true);
        ClientResponse response = new ClientResponse(id, "Ana", TipoDocumento.PESSOA_FISICA, "52998224725",
                "11999999999", "Rua A", true, LocalDateTime.now(), LocalDateTime.now());

        when(clientService.update(eq(id), any(ClientRequest.class))).thenReturn(response);

        mockMvc.perform(put("/api/clientes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()));
    }

    @Test
    void deveBuscarClientePorId() throws Exception {
        UUID id = UUID.randomUUID();
        ClientResponse response = new ClientResponse(id, "Ana", TipoDocumento.PESSOA_FISICA, "52998224725",
                "11999999999", "Rua A", true, LocalDateTime.now(), null);

        when(clientService.findById(id)).thenReturn(response);

        mockMvc.perform(get("/api/clientes/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Ana"));
    }

    @Test
    void deveListarTodos() throws Exception {
        ClientResponse c1 = new ClientResponse(UUID.randomUUID(), "A", TipoDocumento.PESSOA_FISICA, "52998224725",
                "1", "X", true, LocalDateTime.now(), null);
        ClientResponse c2 = new ClientResponse(UUID.randomUUID(), "B", TipoDocumento.PESSOA_JURIDICA, "11444777000161",
                "2", "Y", false, LocalDateTime.now(), null);

        when(clientService.findAll()).thenReturn(List.of(c1, c2));

        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("A"))
                .andExpect(jsonPath("$[1].nome").value("B"));
    }

    @Test
    void deveRemoverCliente() throws Exception {
        UUID id = UUID.randomUUID();
        doNothing().when(clientService).delete(id);

        mockMvc.perform(delete("/api/clientes/{id}", id))
                .andExpect(status().isNoContent());
    }
}
