package br.com.brisabr.helpdesk.model.user.client.dto;

public record ClientCreateDTO(
    String name,
    String address,
    String phone,
    Boolean status,
    String email,
    Boolean isActive
) {}