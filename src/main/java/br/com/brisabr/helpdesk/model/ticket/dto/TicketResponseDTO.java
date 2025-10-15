package br.com.brisabr.helpdesk.model.ticket.dto;

import java.time.LocalDateTime;

public record TicketResponseDTO(
    Long id,
    String title,
    Long slaId,
    Long requesterId,
    Long responsibleEmployeeId,
    Long productId,
    String description,
    String priority,
    LocalDateTime dueDate,
    String status,
    LocalDateTime closedAt,
    Long closedById,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}