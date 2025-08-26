package br.com.brisabr.helpdesk.model.sla.dto;

import java.time.LocalDateTime;

public record SlaResponseDTO(
    Long id,
    String name,
    String description,
    Long responseTime,
    Long resolutionTime,
    Boolean isActive,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}