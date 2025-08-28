package br.com.brisabr.helpdesk.model.user.client.dto;

public record ClientCreateDTO(
    String name,
    String cpf,
    String email,
    String password,
    Boolean isActive
) {}