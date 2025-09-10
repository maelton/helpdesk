package br.com.brisabr.helpdesk.model.ticket.dto;

public record TicketOpeningDTO(
    Long productId,
    String title,
    String description
) {}
