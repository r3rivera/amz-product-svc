package com.boogeyman.app.graphql.models;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserProfileRequest {
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
}
