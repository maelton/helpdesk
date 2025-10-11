package br.com.brisabr.helpdesk.model.employee.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record EmployeeResponseDTO(
    Long userId,
    String firstName,
    String lastName,
    String cpf,
    UUID internalCode,
    String email,
    Boolean isActive,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
