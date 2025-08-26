package br.com.brisabr.helpdesk.controller;

import br.com.brisabr.helpdesk.model.sla.Sla;
import br.com.brisabr.helpdesk.model.sla.dto.SlaCreateDTO;
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
    public ResponseEntity<Sla> getById(@PathVariable Long id) {
        Sla sla = slaService.getById(id);
        return ResponseEntity.ok(sla);
    }

    @GetMapping
    public ResponseEntity<List<Sla>> getAll() {
        List<Sla> slas = slaService.getAll();
        return ResponseEntity.ok(slas);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        slaService.deleteById(id);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}