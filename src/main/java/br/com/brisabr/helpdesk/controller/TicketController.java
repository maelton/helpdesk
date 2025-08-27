package br.com.brisabr.helpdesk.controller;

import br.com.brisabr.helpdesk.model.ticket.dto.TicketClosedDTO;
import br.com.brisabr.helpdesk.model.ticket.dto.TicketCreateDTO;
import br.com.brisabr.helpdesk.model.ticket.dto.TicketResponseDTO;
import br.com.brisabr.helpdesk.model.ticket.dto.TicketUpdateDTO;
import br.com.brisabr.helpdesk.service.TicketService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping
    public ResponseEntity<TicketResponseDTO> create(@Valid @RequestBody TicketCreateDTO dto) {
        TicketResponseDTO created = ticketService.create(dto);
        URI location = URI.create("/api/v1/tickets/" + created.id());
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.findById(id));
    }

    @GetMapping
    public Page<TicketResponseDTO> findAll(Pageable pageable) {
        return ticketService.findAll(pageable);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> update(@PathVariable Long id, @Valid @RequestBody TicketUpdateDTO dto) {
        return ResponseEntity.ok(ticketService.update(id, dto));
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<TicketResponseDTO> close(@PathVariable Long id, @Valid @RequestBody TicketClosedDTO dto) {
        return ResponseEntity.ok(ticketService.close(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }
}