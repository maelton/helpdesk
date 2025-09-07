package br.com.brisabr.helpdesk.service;

import java.time.Instant;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import br.com.brisabr.helpdesk.model.auth.Jwt;

@Service
public class JwtService {
    private final JwtEncoder encoder;
    public JwtService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public Jwt generateJwt(String username) {
        Instant now = Instant.now();
        long expiry = 3600L;

        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("helpdesk.brisabr.com.br")
            .issuedAt(now)
            .expiresAt(now.plusSeconds(expiry))
            .subject(username)
            .build();
        return new Jwt(encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue());
    }
}
