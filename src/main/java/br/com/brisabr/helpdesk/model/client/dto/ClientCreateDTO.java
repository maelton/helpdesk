package br.com.brisabr.helpdesk.model.client.dto;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ClientCreateDTO (
    
    @NotBlank
    String name,

    @CPF
    @NotBlank
    @Size(max=11, min=11)
    String cpf,

    @Email
    @NotBlank
    String email,

    @NotBlank
    String password,
    Boolean isActive
) {}