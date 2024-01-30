package com.boogeyman.app.model;

import lombok.Data;

import java.util.UUID;

@Data
public class AccountUserSchedule {
    private UUID scheduleId;
    private String title;
    private String description;
    private String location;
    private boolean blocked;
    private String startDate;
    private String endDate;
}
