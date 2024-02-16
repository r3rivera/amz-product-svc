package com.boogeyman.app.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SMSRedirectController {


    @GetMapping("/app/sms-login")
    public ResponseEntity<Void> redirectSMS() throws UnsupportedEncodingException, URISyntaxException {
        final String message = "Welcome to SMS";
        final String encodedMessage = URLEncoder.encode(message, "UTF-8");
        return ResponseEntity.status(HttpStatus.FOUND).location(new URI("sms:+18885559000")).build();
    }
}
