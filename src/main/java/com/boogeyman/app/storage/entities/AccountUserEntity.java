package com.boogeyman.app.storage.entities;

import lombok.Data;

import java.util.UUID;

@Data
public class AccountUserEntity extends BaseEntity {
    private UUID acctUserId;
    private UUID acctId;
    private String firstName;
    private String lastName;
}
