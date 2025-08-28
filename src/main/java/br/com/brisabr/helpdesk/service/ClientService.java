package br.com.brisabr.helpdesk.service;

import br.com.brisabr.helpdesk.model.user.client.Client;
import br.com.brisabr.helpdesk.model.user.client.dto.ClientCreateDTO;
import br.com.brisabr.helpdesk.model.user.client.dto.ClientResponseDTO;
import br.com.brisabr.helpdesk.model.user.client.dto.ClientUpdateDTO;
import br.com.brisabr.helpdesk.repository.ClientRepository;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public ClientResponseDTO create(ClientCreateDTO dto) {
        Client entity = toEntity(dto);
        Client saved = clientRepository.save(entity);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public ClientResponseDTO findById(Long id) {
        Client client = clientRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Client not found: " + id));
        return toResponse(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientResponseDTO> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable).map(this::toResponse);
    }

    @Transactional
    public ClientResponseDTO update(Long id, ClientUpdateDTO dto) {
        Client client = clientRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Client not found: " + id));
        updateEntity(client, dto);
        Client saved = clientRepository.save(client);
        return toResponse(saved);
    }

    @Transactional
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException("Client not found: " + id);
        }
        clientRepository.deleteById(id);
    }

    private Client toEntity(ClientCreateDTO dto) {
        Client client = new Client();
        client.setName(dto.name());
        client.setCpf(dto.cpf());
        client.setUsername(dto.email());
        client.setPassword(dto.password());
        client.setIsActive(dto.isActive());
        return client;
    }

    private void updateEntity(Client client, ClientUpdateDTO dto) {
        client.setName(dto.name());
        client.setAddress(dto.address());
        client.setPhone(dto.phone());
        client.setStatus(dto.status());
        // Base User fields (assuming they exist on User)
        client.setUsername(dto.email());
        client.setIsActive(dto.isActive());
    }

    private ClientResponseDTO toResponse(Client client) {
        return new ClientResponseDTO(
            client.getId(),
            client.getName(),
            client.getAddress(),
            client.getPhone(),
            client.getStatus(),
            client.getUsername(),
            client.getIsActive(),
            client.getCreatedAt(),
            client.getUpdatedAt()
        );
    }
}