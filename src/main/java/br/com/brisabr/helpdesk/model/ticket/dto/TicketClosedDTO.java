package br.com.brisabr.helpdesk.model.ticket.dto;

import java.time.LocalDateTime;

public record TicketClosedDTO(
    Long closedById,
    String status,
    LocalDateTime closedAt
) {}