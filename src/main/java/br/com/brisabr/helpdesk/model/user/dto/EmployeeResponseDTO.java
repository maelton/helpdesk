package br.com.brisabr.helpdesk.model.user.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record EmployeeResponseDTO(
    UUID userId,
    String firstName,
    String lastName,
    String cpf,
    String internalCode,
    String email,
    Boolean isActive,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
