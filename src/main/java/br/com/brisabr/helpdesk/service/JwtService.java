package br.com.brisabr.helpdesk.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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

    public Jwt generateJwt(UserDetails userDetails) {
        Instant now = Instant.now();
        long expiry = 3600L;

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("helpdesk.brisabr.com.br")
            .issuedAt(now)
            .expiresAt(now.plusSeconds(expiry))
            .subject(userDetails.getUsername())
            .claim("roles", roles)
            .build();
        return new Jwt(encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue());
    }
}
