package br.com.brisabr.helpdesk.model.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginDTO (
    
    @Email
    @NotBlank
    String username,

    @NotBlank
    String password
) {}
