package com.boogeyman.app.storage.entities;

import lombok.Data;

import java.util.UUID;

@Data
public class AccountEntity extends BaseEntity{

    private UUID acctId;
    private String email;
    private String password;
    private boolean acctLocked;
    private boolean acctActive;

}
