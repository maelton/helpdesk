package br.com.brisabr.helpdesk.model.product.dto;

public record ProductCreateDTO(
    String name,
    Boolean isActive,
    String description,
    Boolean isPhysical,
    Long categoryId
) {}
