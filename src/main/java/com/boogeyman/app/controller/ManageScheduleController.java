package com.boogeyman.app.controller;

import com.boogeyman.app.exceptions.RequestProcessException;
import com.boogeyman.app.model.AccountUserSchedule;
import com.boogeyman.app.model.ScheduleRequest;
import com.boogeyman.app.service.UserAccountService;
import com.boogeyman.app.service.UserSchedulerService;
import com.boogeyman.app.storage.entities.AccountEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manage/users")
public class ManageScheduleController {

    private final UserSchedulerService schedulerService;
    private final UserAccountService accountService;

    @PostMapping("/schedule")
    public ResponseEntity<UUID> createUserSchedule(Authentication auth,
                                                   @RequestBody @Valid ScheduleRequest request){
        log.info("Creating a new schedule!");
        final UUID scheduleId = this.schedulerService.createUserSchedule(getUserUUID(auth.getName()), request);
        return ResponseEntity.ok().body(scheduleId);
    }

    @GetMapping("/schedule")
    public ResponseEntity<List<AccountUserSchedule>> getUserSchedule(Authentication auth){
        log.info("Creating a new schedule!");
        final List<AccountUserSchedule> schedules = this.schedulerService.getUserSchedule(getUserUUID(auth.getName()));
        if(!schedules.isEmpty()){
            return ResponseEntity.ok(schedules);
        }
        return ResponseEntity.noContent().build();
    }

    private UUID getUserUUID(String name){
        final Optional<AccountEntity> entity = this.accountService.getUserAccount(name);
        if(entity.isPresent()){
            return entity.get().getAcctId();
        }
        log.error("User is not found! Unable to schedule!");
        throw new RequestProcessException("Unable to schedule a record!");
    }
}
