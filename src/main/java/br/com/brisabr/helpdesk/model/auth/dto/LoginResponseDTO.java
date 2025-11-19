package br.com.brisabr.helpdesk.model.auth.dto;

import java.util.Set;

import br.com.brisabr.helpdesk.model.user.enums.Role;

public record LoginResponseDTO(
    Long userId,
    String email,
    String name,
    String cpf,
    Set<Role> roles,
    String jwt
) {}