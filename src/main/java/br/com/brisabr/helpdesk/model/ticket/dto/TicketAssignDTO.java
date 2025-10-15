package br.com.brisabr.helpdesk.model.ticket.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TicketAssignDTO(
    @NotNull(message = "Employee ID is required")
    @Positive(message = "Employee ID must be positive")
    Long employeeId
) {
}