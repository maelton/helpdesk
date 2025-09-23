package br.com.brisabr.helpdesk.service;

import br.com.brisabr.helpdesk.model.sla.Sla;
import br.com.brisabr.helpdesk.model.sla.dto.SlaCreateDTO;
import br.com.brisabr.helpdesk.model.sla.dto.SlaUpdateDTO;
import br.com.brisabr.helpdesk.repository.SlaRepository;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SlaService {

    private final SlaRepository slaRepository;

    @Transactional
    public Sla create(SlaCreateDTO dto) {
        Sla sla = toEntity(dto);
        return slaRepository.save(sla);
    }

    @Transactional(readOnly = true)
    public Sla getById(Long id) {
        return slaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("SLA not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Sla> getAll() {
        return slaRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        if (!slaRepository.existsById(id)) {
            throw new EntityNotFoundException("SLA not found with id: " + id);
        }
        slaRepository.deleteById(id);
    }

    @Transactional
    public Sla update(Long id, SlaUpdateDTO dto) {
        Sla sla = slaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("SLA not found with id: " + id));
        
        updateEntity(sla, dto);
        return slaRepository.save(sla);
    }

    private Sla toEntity(SlaCreateDTO dto) {
        Sla sla = new Sla();
        sla.setName(dto.name());
        sla.setDescription(dto.description());
        sla.setResponseTime(dto.responseTime());
        sla.setResolutionTime(dto.resolutionTime());
        sla.setIsActive(dto.isActive() != null && dto.isActive());
        sla.setCreatedAt(LocalDateTime.now());
        sla.setUpdatedAt(LocalDateTime.now());
        return sla;
    }

    private void updateEntity(Sla sla, SlaUpdateDTO dto) {
        if (dto.name() != null) {
            sla.setName(dto.name());
        }
        if (dto.description() != null) {
            sla.setDescription(dto.description());
        }
        if (dto.responseTime() != null) {
            sla.setResponseTime(dto.responseTime());
        }
        if (dto.resolutionTime() != null) {
            sla.setResolutionTime(dto.resolutionTime());
        }
        if (dto.isActive() != null) {
            sla.setIsActive(dto.isActive());
        }
        sla.setUpdatedAt(LocalDateTime.now());
    }
}