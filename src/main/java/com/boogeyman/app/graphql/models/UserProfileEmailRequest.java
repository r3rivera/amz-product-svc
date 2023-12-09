package com.boogeyman.app.graphql.models;

import lombok.Data;

@Data
public class UserProfileEmailRequest {

    private UserProfileRequest profile;
    private String email;

}
