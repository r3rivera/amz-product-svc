package com.boogeyman.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/app")
public class HealthCheckController {

    @GetMapping("/healthcheck")
    public ResponseEntity<String> getApp(){
        log.info("Getting the HealthCheck...");
        return new ResponseEntity<>("Success Health", HttpStatus.OK);
    }

}
