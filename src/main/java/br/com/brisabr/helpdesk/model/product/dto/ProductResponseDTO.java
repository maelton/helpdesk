package br.com.brisabr.helpdesk.model.product.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponseDTO(
    Long id,
    UUID internalCode,
    String name,
    Boolean isActive,
    String description,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
