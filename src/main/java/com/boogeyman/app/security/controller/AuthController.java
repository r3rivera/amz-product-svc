package com.boogeyman.app.security.controller;

import com.boogeyman.app.security.service.AuthTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthTokenService tokenService;

    /**
     * This is used to perform the necessary authentication
     * @param authentication
     * @return
     */
    @PostMapping("/authtoken")
    public ResponseEntity<AuthToken> generateToken(Authentication authentication){
        final String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));
        final String token = this.tokenService.generateToken(authentication.getName(), scope);
        return ResponseEntity.ok(new AuthToken(token));
    }

    record AuthToken(String jwtToken){}

}
