package br.com.brisabr.helpdesk.service;

import br.com.brisabr.helpdesk.model.auth.Jwt;
import br.com.brisabr.helpdesk.model.auth.LoginResponseDTO;
import br.com.brisabr.helpdesk.model.user.dto.UserLoginDTO;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public LoginResponseDTO authenticate(UserLoginDTO loginDetails) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginDetails.username(),
                    loginDetails.password()
                )
            );
            Jwt jwt = jwtService.generateJwt(authentication);
            return new LoginResponseDTO(jwt.token());
        } catch (AuthenticationException e) {
            throw new RuntimeException("Email or password incorrect!");
        }
    }
}
