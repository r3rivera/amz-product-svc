package com.boogeyman.app.storage.entities;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AppUserEntity {

    private UUID id;
    private String userName;
    private String firstName;
    private String lastName;
    private boolean locked;
    private boolean active;
    private LocalDateTime createDate;
}
