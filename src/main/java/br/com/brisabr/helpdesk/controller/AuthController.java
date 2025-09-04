package br.com.brisabr.helpdesk.controller;

import br.com.brisabr.helpdesk.model.auth.LoginResponseDTO;
import br.com.brisabr.helpdesk.model.user.dto.UserLoginDTO;
import br.com.brisabr.helpdesk.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody UserLoginDTO loginDetails) {
        return ResponseEntity.ok(authService.authenticate(loginDetails));
    }
}
