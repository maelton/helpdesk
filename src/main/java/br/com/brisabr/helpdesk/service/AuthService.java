package br.com.brisabr.helpdesk.service;

import br.com.brisabr.helpdesk.model.auth.Jwt;
import br.com.brisabr.helpdesk.model.auth.dto.LoginResponseDTO;
import br.com.brisabr.helpdesk.model.client.Client;
import br.com.brisabr.helpdesk.model.user.dto.UserLoginDTO;
import br.com.brisabr.helpdesk.model.employee.Employee;

import java.util.stream.Collectors;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeService employeeService;
    private final ClientService clientService;

    public AuthService (
            JwtService jwtService,
            UserDetailsService userDetailsService, 
            PasswordEncoder passwordEncoder,
            EmployeeService employeeService,
            ClientService clientService) 
    {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.employeeService = employeeService;
        this.clientService = clientService;
    }

    public LoginResponseDTO authenticate(UserLoginDTO loginDetails) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginDetails.username());       
            if (!passwordEncoder.matches(loginDetails.password(), userDetails.getPassword())) {
                throw new BadCredentialsException("Invalid credentials");
            }
            Jwt jwt = jwtService.generateJwt(userDetails);
            if (employeeService.existsByUsername(userDetails.getUsername())) {
                Employee employee = employeeService.getByUsername(userDetails.getUsername());
                return new LoginResponseDTO(
                    employee.getId(),
                    employee.getUsername(),
                    employee.getFirstName() + " " + employee.getLastName(),
                    employee.getCpf(),
                    employee.getRoles().stream().map(userRole -> userRole.getName()).collect(Collectors.toSet()),
                    jwt.token());
            } else {
                Client client = clientService.getByUsername(userDetails.getUsername());
                return new LoginResponseDTO(
                    client.getId(),
                    client.getUsername(),
                    client.getName(),
                    client.getTaxId(),
                    client.getRoles().stream().map(userRole -> userRole.getName()).collect(Collectors.toSet()),
                    jwt.token());
            }
        } catch (AuthenticationException e) {
            throw new RuntimeException("Email or password incorrect!");
        }
    }
}