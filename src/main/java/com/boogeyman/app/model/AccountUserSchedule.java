package com.boogeyman.app.model;

import lombok.Data;

@Data
public class AccountUserSchedule {
    private String title;
    private String description;
    private String location;
    private boolean blocked;
    private String startDate;
    private String endDate;
}
