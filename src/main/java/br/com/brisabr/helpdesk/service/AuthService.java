package br.com.brisabr.helpdesk.service;

import br.com.brisabr.helpdesk.model.auth.Jwt;
import br.com.brisabr.helpdesk.model.auth.LoginResponseDTO;
import br.com.brisabr.helpdesk.model.user.dto.UserLoginDTO;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService; // Add this
    private final PasswordEncoder passwordEncoder;

    public AuthService (
            JwtService jwtService,
            UserDetailsService userDetailsService, 
            PasswordEncoder passwordEncoder) 
    {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDTO authenticate(UserLoginDTO loginDetails) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginDetails.username());       
            if (!passwordEncoder.matches(loginDetails.password(), userDetails.getPassword())) {
                throw new BadCredentialsException("Invalid credentials");
            }
            Jwt jwt = jwtService.generateJwt(userDetails.getUsername());
            return new LoginResponseDTO(jwt.token());
        } catch (AuthenticationException e) {
            throw new RuntimeException("Email or password incorrect!");
        }
    }
}