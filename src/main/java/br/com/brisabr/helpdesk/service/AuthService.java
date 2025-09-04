package br.com.brisabr.helpdesk.service;

import br.com.brisabr.helpdesk.model.auth.Jwt;
import br.com.brisabr.helpdesk.model.auth.LoginResponseDTO;
import br.com.brisabr.helpdesk.model.user.dto.UserLoginDTO;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService; // Add this
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager,
                       JwtService jwtService,
                       UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDTO authenticate(UserLoginDTO loginDetails) {
        try {
            // Load user details directly instead of using AuthenticationManager recursively
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginDetails.username());
            
            // Perform password verification
            if (!passwordEncoder.matches(loginDetails.password(), userDetails.getPassword())) {
                throw new BadCredentialsException("Invalid credentials");
            }

            // Generate JWT if authentication successful
            Jwt jwt = jwtService.generateJwt(userDetails.getUsername());
            return new LoginResponseDTO(jwt.token());
        } catch (AuthenticationException e) {
            throw new RuntimeException("Email or password incorrect!");
        }
    }
}