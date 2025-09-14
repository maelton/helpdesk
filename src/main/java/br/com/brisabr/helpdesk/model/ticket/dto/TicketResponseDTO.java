package br.com.brisabr.helpdesk.model.ticket.dto;

import java.time.LocalDateTime;

public record TicketResponseDTO(
    Long id,
    String title,
    Long slaId,
    Long requesterId,
    Long responsibleEmployeeId,
    Long productId,
    Long closedById,
    String description,
    String priority,
    LocalDateTime dueDate,
    String status,
    LocalDateTime closedAt,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}