package com.boogeyman.app.graphql.models;

import lombok.Data;

import java.util.UUID;

@Data
public class UserProfile {
    private UUID acctId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
}
