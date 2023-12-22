package com.boogeyman.app.model;

import lombok.Data;

@Data
public class AccountUserRequest {

    private String email;
    private String password;
    private String firstName;
    private String lastName;


}
