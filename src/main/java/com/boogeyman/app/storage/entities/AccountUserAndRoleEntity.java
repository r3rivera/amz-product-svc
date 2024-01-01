package com.boogeyman.app.storage.entities;

import java.util.List;

public record AccountUserAndRoleEntity(AccountEntity accountEntity, List<String> roleEntity){}

