package com.boogeyman.app.graphql.models;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UserProfile implements UProfile{
    private UUID id;
    private String userName;
    private String firstName;
    private String lastName;
    private Address homeAddress;
    private ContactInfo email;
    private ContactInfo phone;

    @Override
    public String getUserName() {
        return this.userName;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }
}
