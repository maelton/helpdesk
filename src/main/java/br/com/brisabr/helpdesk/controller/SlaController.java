package br.com.brisabr.helpdesk.controller;

import br.com.brisabr.helpdesk.model.sla.Sla;
import br.com.brisabr.helpdesk.model.sla.SlaPriority;
import br.com.brisabr.helpdesk.model.sla.SlaCalendar;
import br.com.brisabr.helpdesk.model.sla.dto.SlaCreateDTO;
import br.com.brisabr.helpdesk.model.sla.dto.SlaUpdateDTO;
import br.com.brisabr.helpdesk.model.sla.dto.SlaResponseDTO;
import br.com.brisabr.helpdesk.service.SlaService;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/slas")
@RequiredArgsConstructor
public class SlaController {

    private final SlaService slaService;

    @PostMapping
    public ResponseEntity<Sla> create(@RequestBody SlaCreateDTO dto) {
        Sla created = slaService.create(dto);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(created.getId())
            .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlaResponseDTO> getById(@PathVariable Long id) {
        SlaResponseDTO sla = slaService.getById(id);
        return ResponseEntity.ok(sla);
    }

    @GetMapping
    public ResponseEntity<List<SlaResponseDTO>> getAll() {
        List<SlaResponseDTO> slas = slaService.getAll();
        return ResponseEntity.ok(slas);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        slaService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sla> update(@PathVariable Long id, @RequestBody SlaUpdateDTO dto) {
        Sla updated = slaService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/priorities")
    public ResponseEntity<List<SlaPriority>> getAllPriorities() {
        List<SlaPriority> priorities = slaService.getAllPriorities();
        return ResponseEntity.ok(priorities);
    }

    @GetMapping("/calendars")
    public ResponseEntity<List<SlaCalendar>> getAllCalendars() {
        List<SlaCalendar> calendars = slaService.getAllCalendars();
        return ResponseEntity.ok(calendars);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}