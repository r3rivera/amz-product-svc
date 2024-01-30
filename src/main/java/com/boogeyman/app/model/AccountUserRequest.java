package com.boogeyman.app.model;

import com.boogeyman.app.constraints.Email;
import lombok.Data;

@Data
public class AccountUserRequest {
    @Email
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
