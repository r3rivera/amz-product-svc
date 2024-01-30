package com.boogeyman.app.service;

import com.boogeyman.app.model.AccountUserSchedule;
import com.boogeyman.app.model.ScheduleRequest;
import com.boogeyman.app.storage.entities.AccountScheduleEntity;
import com.boogeyman.app.storage.service.AccountUserScheduleStorageService;
import com.boogeyman.app.types.DatePatternTypes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserSchedulerService {

    private final AccountUserScheduleStorageService scheduleStorageService;

    public UUID createUserSchedule(UUID acctId, ScheduleRequest request){
        final AccountScheduleEntity entity = new AccountScheduleEntity();
        entity.setAcctId(acctId);
        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setLocation(request.getLocation());
        entity.setBlocked(request.isBlocked());
        entity.setStartDate(convertStringTime(request.getStartDate()));
        entity.setEndDate(convertStringTime(request.getEndDate()));
        return scheduleStorageService.createScheduleRecord(entity);
    }


    public List<AccountUserSchedule> getUserSchedule(UUID acctId){
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DatePatternTypes.DEFAULT_DATE_TIME_PATTERN);
        return scheduleStorageService.getUserSchedules(acctId).stream().map(item -> {
            final AccountUserSchedule schedule = new AccountUserSchedule();
            schedule.setTitle(item.getTitle());
            schedule.setDescription(item.getDescription());
            schedule.setLocation(item.getLocation());
            schedule.setBlocked(item.isBlocked());
            schedule.setStartDate(item.getStartDate().format(formatter));
            schedule.setEndDate(item.getEndDate().format(formatter));
            return schedule;
        }).collect(Collectors.toList());
    }

    private LocalDateTime convertStringTime(String time){
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DatePatternTypes.DEFAULT_DATE_TIME_PATTERN);
        return LocalDateTime.parse(time, formatter);
    }

}
