package com.boogeyman.app.storage.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BaseEntity {
    private LocalDate createdDate;
    private String createdBy;
    private LocalDate modifiedBy;
    private String modifiedDate;
}
