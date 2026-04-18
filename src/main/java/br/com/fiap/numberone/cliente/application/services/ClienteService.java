package br.com.fiap.numberone.cliente.application.services;

import br.com.fiap.numberone.cliente.api.dtos.requests.ClienteRequest;
import br.com.fiap.numberone.cliente.api.dtos.responses.ClienteResponse;
import br.com.fiap.numberone.cliente.application.mappers.ClienteMapper;
import br.com.fiap.numberone.cliente.domain.entities.Cliente;
import br.com.fiap.numberone.cliente.domain.validators.DocumentoValidator;
import br.com.fiap.numberone.cliente.infrastructure.repositories.ClienteRepository;
import br.com.fiap.numberone.shared.api.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepository clienteRepository, ClienteMapper clienteMapper) {
        this.clienteRepository = clienteRepository;
        this.clienteMapper = clienteMapper;
    }

    public ClienteResponse create(ClienteRequest request) {
        DocumentoValidator.validar(request.tipoDocumento(), request.documento());

        Cliente cliente = clienteMapper.toEntity(request);
        Cliente saved = clienteRepository.save(cliente);

        return clienteMapper.toResponse(saved);
    }

    public ClienteResponse update(Long id, ClienteRequest request) {
        DocumentoValidator.validar(request.tipoDocumento(), request.documento());

        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id: " + id));

        Cliente clienteAtualizado = clienteExistente.updateFrom(clienteMapper.toEntity(request));
        Cliente saved = clienteRepository.save(clienteAtualizado);

        return clienteMapper.toResponse(saved);
    }

    public ClienteResponse findById(Long id) {
        return clienteRepository.findById(id)
                .map(clienteMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id: " + id));
    }

    public List<ClienteResponse> findAll() {
        return clienteRepository.findAll()
                .stream()
                .map(clienteMapper::toResponse)
                .toList();
    }

    public void delete(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id: " + id));

        clienteRepository.delete(cliente);
    }
}
