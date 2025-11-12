package br.com.brisabr.helpdesk.controller;

import java.net.URI;
import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.brisabr.helpdesk.model.ticket.dto.TicketAssignDTO;
import br.com.brisabr.helpdesk.model.ticket.dto.TicketClosedDTO;
import br.com.brisabr.helpdesk.model.ticket.dto.TicketCreateDTO;
import br.com.brisabr.helpdesk.model.ticket.dto.TicketOpeningDTO;
import br.com.brisabr.helpdesk.model.ticket.dto.TicketResponseDTO;
import br.com.brisabr.helpdesk.model.ticket.dto.TicketUpdateDTO;
import br.com.brisabr.helpdesk.service.TicketService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

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

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/open")
    public ResponseEntity<TicketResponseDTO> openTicket(@Valid @RequestBody TicketOpeningDTO dto, @AuthenticationPrincipal Jwt requesterJwt) {
        TicketResponseDTO opened = ticketService.openTicket(dto, requesterJwt.getSubject());
        return ResponseEntity.status(HttpStatus.CREATED).body(opened);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.findById(id));
    }

    @GetMapping
    public Page<TicketResponseDTO> findAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int pageSize
    ) {

        Sort.Direction direction = Sort.Direction.ASC;
        Sort sort = Sort.by(direction, "id");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
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

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/{id}/assign")
    public ResponseEntity<TicketResponseDTO> assignTicket(
            @PathVariable Long id,
            @Valid @RequestBody TicketAssignDTO dto) {
        TicketResponseDTO assigned = ticketService.assignTicket(id, dto);
        return ResponseEntity.ok(assigned);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }
}