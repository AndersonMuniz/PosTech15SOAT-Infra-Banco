package br.com.fiap.numberone.client.application.services;

import br.com.fiap.numberone.client.api.dtos.requests.ClientRequest;
import br.com.fiap.numberone.client.api.dtos.responses.ClientResponse;
import br.com.fiap.numberone.client.application.mappers.ClientMapper;
import br.com.fiap.numberone.client.domain.entities.Cliente;
import br.com.fiap.numberone.client.domain.validators.DocumentoValidator;
import br.com.fiap.numberone.client.infrastructure.persistence.entities.ClienteEntity;
import br.com.fiap.numberone.client.infrastructure.persistence.mappers.ClienteEntityMapper;
import br.com.fiap.numberone.client.infrastructure.repositories.ClienteRepository;
import br.com.fiap.numberone.shared.api.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    private final ClienteRepository clienteRepository;
    private final ClientMapper clientMapper;
    private final ClienteEntityMapper clienteEntityMapper;

    public ClientService(ClienteRepository clienteRepository, ClientMapper clientMapper, ClienteEntityMapper clienteEntityMapper) {
        this.clienteRepository = clienteRepository;
        this.clientMapper = clientMapper;
        this.clienteEntityMapper = clienteEntityMapper;
    }

    public ClientResponse create(ClientRequest request) {
        DocumentoValidator.validar(request.tipoDocumento(), request.documento());

        Cliente cliente = clientMapper.toEntity(request);
        ClienteEntity saved = clienteRepository.save(clienteEntityMapper.toEntity(cliente));

        return clientMapper.toResponse(clienteEntityMapper.toDomain(saved));
    }

    public ClientResponse update(UUID id, ClientRequest request) {
        DocumentoValidator.validar(request.tipoDocumento(), request.documento());

        Cliente clienteExistente = clienteRepository.findById(id)
                .map(clienteEntityMapper::toDomain)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id: " + id));

        Cliente clienteAtualizado = clienteExistente.updateFrom(clientMapper.toEntity(request));
        ClienteEntity saved = clienteRepository.save(clienteEntityMapper.toEntity(clienteAtualizado));

        return clientMapper.toResponse(clienteEntityMapper.toDomain(saved));
    }

    public ClientResponse findById(UUID id) {
        return clienteRepository.findById(id)
                .map(clienteEntityMapper::toDomain)
                .map(clientMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id: " + id));
    }

    public List<ClientResponse> findAll() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteEntityMapper::toDomain)
                .map(clientMapper::toResponse)
                .toList();
    }

    public void delete(UUID id) {
        ClienteEntity cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id: " + id));

        clienteRepository.delete(cliente);
    }
}
