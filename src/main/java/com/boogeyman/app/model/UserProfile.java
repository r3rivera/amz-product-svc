package com.boogeyman.app.model;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class UserProfile {

    private UUID id;
    private String firstName;
    private String lastName;
}
