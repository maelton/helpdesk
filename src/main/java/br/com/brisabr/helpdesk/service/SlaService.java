package br.com.brisabr.helpdesk.service;

import br.com.brisabr.helpdesk.model.sla.Sla;
import br.com.brisabr.helpdesk.model.sla.SlaPriority;
import br.com.brisabr.helpdesk.model.sla.SlaCalendar;
import br.com.brisabr.helpdesk.model.sla.dto.SlaCreateDTO;
import br.com.brisabr.helpdesk.model.sla.dto.SlaUpdateDTO;
import br.com.brisabr.helpdesk.model.sla.dto.SlaResponseDTO;
import br.com.brisabr.helpdesk.model.sla.dto.SlaPriorityResponseDTO;
import br.com.brisabr.helpdesk.model.sla.dto.SlaCalendarResponseDTO;
import br.com.brisabr.helpdesk.model.sla.dto.SlaDayResponseDTO;
import br.com.brisabr.helpdesk.repository.SlaRepository;
import br.com.brisabr.helpdesk.repository.SlaPriorityRepository;
import br.com.brisabr.helpdesk.repository.SlaCalendarRepository;

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
    private final SlaPriorityRepository slaPriorityRepository;
    private final SlaCalendarRepository slaCalendarRepository;

    @Transactional
    public Sla create(SlaCreateDTO dto) {
        Sla sla = toEntity(dto);
        return slaRepository.save(sla);
    }

    @Transactional(readOnly = true)
    public Sla getEntityById(Long id) {
        return slaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("SLA not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<SlaResponseDTO> getAll() {
        return slaRepository.findAll().stream()
            .map(this::toResponseDTO)
            .toList();
    }

    @Transactional(readOnly = true)
    public SlaResponseDTO getById(Long id) {
        Sla sla = slaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("SLA not found with id: " + id));
        return toResponseDTO(sla);
    }

    @Transactional(readOnly = true)
    public List<SlaPriority> getAllPriorities() {
        return slaPriorityRepository.findByIsActiveTrueOrderByLevel();
    }

    @Transactional(readOnly = true)
    public List<SlaCalendar> getAllCalendars() {
        return slaCalendarRepository.findByIsActiveTrueOrderByName();
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
        
        if (dto.priorityId() != null) {
            SlaPriority priority = slaPriorityRepository.findById(dto.priorityId())
                .orElseThrow(() -> new EntityNotFoundException("SLA Priority not found with id: " + dto.priorityId()));
            sla.setPriority(priority);
        }
        
        if (dto.calendarId() != null) {
            SlaCalendar calendar = slaCalendarRepository.findById(dto.calendarId())
                .orElseThrow(() -> new EntityNotFoundException("SLA Calendar not found with id: " + dto.calendarId()));
            sla.setCalendar(calendar);
        }
        
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
        
        if (dto.priorityId() != null) {
            SlaPriority priority = slaPriorityRepository.findById(dto.priorityId())
                .orElseThrow(() -> new EntityNotFoundException("SLA Priority not found with id: " + dto.priorityId()));
            sla.setPriority(priority);
        }
        
        if (dto.calendarId() != null) {
            SlaCalendar calendar = slaCalendarRepository.findById(dto.calendarId())
                .orElseThrow(() -> new EntityNotFoundException("SLA Calendar not found with id: " + dto.calendarId()));
            sla.setCalendar(calendar);
        }
        
        sla.setUpdatedAt(LocalDateTime.now());
    }

    private SlaResponseDTO toResponseDTO(Sla sla) {
        SlaPriorityResponseDTO priorityDTO = null;
        if (sla.getPriority() != null) {
            priorityDTO = new SlaPriorityResponseDTO(
                sla.getPriority().getId(),
                sla.getPriority().getName(),
                sla.getPriority().getDescription(),
                sla.getPriority().getLevel(),
                sla.getPriority().getColor()
            );
        }

        SlaCalendarResponseDTO calendarDTO = null;
        if (sla.getCalendar() != null) {
            List<SlaDayResponseDTO> dayDTOs = sla.getCalendar().getSlaDays() != null ?
                sla.getCalendar().getSlaDays().stream()
                    .map(day -> new SlaDayResponseDTO(
                        day.getId(),
                        day.getDayOfWeek(),
                        day.getStartTime(),
                        day.getEndTime(),
                        day.getIsWorkingDay()
                    ))
                    .toList() : List.of();

            calendarDTO = new SlaCalendarResponseDTO(
                sla.getCalendar().getId(),
                sla.getCalendar().getName(),
                sla.getCalendar().getDescription(),
                sla.getCalendar().getTimezone(),
                sla.getCalendar().getConsiderWeekends(),
                sla.getCalendar().getConsiderHolidays(),
                dayDTOs
            );
        }

        return new SlaResponseDTO(
            sla.getId(),
            sla.getName(),
            sla.getDescription(),
            sla.getResponseTime(),
            sla.getResolutionTime(),
            sla.getIsActive(),
            priorityDTO,
            calendarDTO,
            sla.getCreatedAt(),
            sla.getUpdatedAt()
        );
    }
}