package com.boogeyman.app.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class AppUserRequest {
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String email;
}
