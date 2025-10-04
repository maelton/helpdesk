package br.com.brisabr.helpdesk.model.client.dto;

public record ClientUpdateDTO(
    String name,
    String address,
    String phone,
    Boolean status,
    String email,
    Boolean isActive
) {}