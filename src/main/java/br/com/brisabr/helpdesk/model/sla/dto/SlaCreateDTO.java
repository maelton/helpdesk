package br.com.brisabr.helpdesk.model.sla.dto;

public record SlaCreateDTO(
    String name,
    String description,
    Long responseTime,
    Long resolutionTime,
    Boolean isActive,
    Long priorityId,
    Long calendarId
) {}