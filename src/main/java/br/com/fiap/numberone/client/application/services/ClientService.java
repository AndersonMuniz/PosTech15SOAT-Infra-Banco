package br.com.fiap.numberone.client.application.services;

import br.com.fiap.numberone.client.api.dtos.requests.ClientRequest;
import br.com.fiap.numberone.client.api.dtos.responses.ClientResponse;
import br.com.fiap.numberone.client.application.mappers.ClientMapper;
import br.com.fiap.numberone.client.domain.entities.Client;
import br.com.fiap.numberone.client.domain.validators.DocumentoValidator;
import br.com.fiap.numberone.client.infrastructure.persistence.entities.ClientEntity;
import br.com.fiap.numberone.client.infrastructure.persistence.mappers.ClientEntityMapper;
import br.com.fiap.numberone.client.infrastructure.repositories.ClientRepository;
import br.com.fiap.numberone.shared.api.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ClientEntityMapper clientEntityMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper, ClientEntityMapper clientEntityMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.clientEntityMapper = clientEntityMapper;
    }

    public ClientResponse create(ClientRequest request) {
        DocumentoValidator.validar(request.tipoDocumento(), request.documento());

        Client client = clientMapper.toEntity(request);
        ClientEntity saved = clientRepository.save(clientEntityMapper.toEntity(client));

        return clientMapper.toResponse(clientEntityMapper.toDomain(saved));
    }

    public ClientResponse update(UUID id, ClientRequest request) {
        DocumentoValidator.validar(request.tipoDocumento(), request.documento());

        Client clientExistente = clientRepository.findById(id)
                .map(clientEntityMapper::toDomain)
                .orElseThrow(() -> new ResourceNotFoundException("Client não encontrado com id: " + id));

        Client clientAtualizado = clientExistente.updateFrom(clientMapper.toEntity(request));
        ClientEntity saved = clientRepository.save(clientEntityMapper.toEntity(clientAtualizado));

        return clientMapper.toResponse(clientEntityMapper.toDomain(saved));
    }

    public ClientResponse findById(UUID id) {
        return clientRepository.findById(id)
                .map(clientEntityMapper::toDomain)
                .map(clientMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Client não encontrado com id: " + id));
    }

    public List<ClientResponse> findAll() {
        return clientRepository.findAll()
                .stream()
                .map(clientEntityMapper::toDomain)
                .map(clientMapper::toResponse)
                .toList();
    }

    public void delete(UUID id) {
        ClientEntity client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client não encontrado com id: " + id));

        clientRepository.delete(client);
    }
}
