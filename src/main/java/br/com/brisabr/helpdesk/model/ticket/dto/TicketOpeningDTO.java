package br.com.brisabr.helpdesk.model.ticket.dto;

public record TicketOpeningDTO(
    Long categoryId,
    Long productId,
    String title,
    String description
) {}
