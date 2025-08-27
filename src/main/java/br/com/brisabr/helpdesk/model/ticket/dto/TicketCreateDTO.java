package br.com.brisabr.helpdesk.model.ticket.dto;

import java.time.LocalDateTime;

public record TicketCreateDTO(
    String title,
    Long slaId,
    Long requesterId,
    Long productId,
    String description,
    String priority,
    LocalDateTime dueDate,
    String status
) {}