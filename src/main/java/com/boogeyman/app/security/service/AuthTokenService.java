package com.boogeyman.app.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthTokenService {

    @Value("${app.token.issuer:self}")
    private String appIssuer;
    private final JwtEncoder encoder;

    public String generateToken(String userName, String scopes){
        final Instant now = Instant.now();
        final long expiration = 900L;

        final JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer(appIssuer)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiration))
                .subject(userName)
                .claim("scope", scopes)
                .build();
        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
