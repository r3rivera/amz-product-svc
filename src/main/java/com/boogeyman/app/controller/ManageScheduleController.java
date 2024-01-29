package com.boogeyman.app.controller;

import com.boogeyman.app.model.ScheduleRequest;
import com.boogeyman.app.service.UserSchedulerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/app/manage")
public class ManageScheduleController {

    private final UserSchedulerService schedulerService;

    @PostMapping("/schedule")
    public ResponseEntity<UUID> createUserSchedule(Authentication auth,
                                                   @RequestBody @Valid ScheduleRequest request){
        log.info("Creating a new schedule!");
        return null;
    }
}
