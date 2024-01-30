package com.boogeyman.app.storage.entities;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AccountScheduleEntity extends BaseEntity{
    private UUID acctId;
    private String title;
    private String description;
    private String location;
    private boolean blocked;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
