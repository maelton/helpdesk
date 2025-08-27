package br.com.brisabr.helpdesk.model.user.client.dto;

import java.time.LocalDateTime;

public record ClientResponseDTO(
    Long userId,
    String name,
    String address,
    String phone,
    Boolean status,
    String email,
    Boolean isActive,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}