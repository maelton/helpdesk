package br.com.brisabr.helpdesk.controller.user;

import br.com.brisabr.helpdesk.model.user.client.dto.ClientCreateDTO;
import br.com.brisabr.helpdesk.model.user.client.dto.ClientResponseDTO;
import br.com.brisabr.helpdesk.model.user.client.dto.ClientUpdateDTO;
import br.com.brisabr.helpdesk.service.ClientService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientResponseDTO> create(@Valid @RequestBody ClientCreateDTO dto) {
        ClientResponseDTO created = clientService.create(dto);
        URI location = URI.create("/api/v1/clients/" + created.userId());
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @GetMapping
    public Page<ClientResponseDTO> findAll(Pageable pageable) {
        return clientService.findAll(pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ClientUpdateDTO dto) {
        return ResponseEntity.ok(clientService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}