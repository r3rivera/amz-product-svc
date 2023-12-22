package com.boogeyman.app.controller;

import com.boogeyman.app.model.ConfigProps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/app")
public class HealthCheckController {


    private final ConfigProps props;
    @GetMapping("/healthcheck")
    public ResponseEntity<ConfigProps> getApp(){
        log.info("Getting the HealthCheck...");
        return new ResponseEntity<>(props, HttpStatus.OK);
    }

}
