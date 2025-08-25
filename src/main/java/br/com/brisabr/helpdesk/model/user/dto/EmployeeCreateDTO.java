package br.com.brisabr.helpdesk.model.user.dto;

public record EmployeeCreateDTO(
    String firstName,
    String lastName,
    String cpf,
    String email,
    Boolean isActive
) {}