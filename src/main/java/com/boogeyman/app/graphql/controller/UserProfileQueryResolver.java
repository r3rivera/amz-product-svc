package com.boogeyman.app.graphql.controller;

import com.boogeyman.app.graphql.models.UserProfile;
import com.boogeyman.app.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserProfileQueryResolver {

    private final UserAccountService accountService;

    @SchemaMapping
    public String firstName(UserProfile profile){
        log.info("Resolving firstName attribute!");
        return this.accountService.getUserByAcctId(profile.getAcctId()).getFirstName();
    }

    @SchemaMapping
    public String lastName(UserProfile profile){
        log.info("Resolving lastName attribute!");
        return this.accountService.getUserByAcctId(profile.getAcctId()).getLastName();
    }

    @SchemaMapping
    public String email(UserProfile profile){
        log.info("Resolving email attribute!");
        return profile.getEmail();
    }
}
