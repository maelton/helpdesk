package br.com.brisabr.helpdesk.model.sla.dto;

public record SlaPriorityResponseDTO(
    Long id,
    String name,
    String description,
    Integer level,
    String color
) {}
