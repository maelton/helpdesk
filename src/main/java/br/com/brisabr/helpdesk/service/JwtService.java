package br.com.brisabr.helpdesk.service;

import br.com.brisabr.helpdesk.model.auth.Jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private final JwtEncoder encoder;
    public JwtService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public Jwt generateJwt(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 3600L;

        String scopes = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("helpdesk.brisabr.com.br")
            .issuedAt(now)
            .expiresAt(now.plusSeconds(expiry))
            .subject(authentication.getPrincipal().toString())
            .claim("scopes", scopes)
            .build();
        return new Jwt(encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue());
    }
}
