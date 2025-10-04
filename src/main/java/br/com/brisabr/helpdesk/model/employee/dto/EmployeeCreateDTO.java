package br.com.brisabr.helpdesk.model.employee.dto;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmployeeCreateDTO (
    
    @NotBlank
    String firstName,

    @NotBlank
    String lastName,

    @CPF
    @NotBlank
    @Size(max=11, min=11)
    String cpf,
    
    @Email
    String email,
    Boolean isActive
) {}