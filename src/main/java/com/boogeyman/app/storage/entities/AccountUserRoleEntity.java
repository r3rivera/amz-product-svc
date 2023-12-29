package com.boogeyman.app.storage.entities;

import lombok.Data;

import java.util.UUID;

@Data
public class AccountUserRoleEntity extends BaseEntity {
    private UUID acctRoleId;
    private UUID acctId;
    private String roleName;
}
