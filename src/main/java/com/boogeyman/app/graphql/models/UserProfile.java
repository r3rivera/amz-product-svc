package com.boogeyman.app.graphql.models;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class UserProfile {
    private UUID id;
    private String firstName;
    private String lastName;
    private Address homeAddress;
    private ContactInfo email;
    private ContactInfo phone;
}
