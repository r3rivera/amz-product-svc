package com.boogeyman.app.graphql.controller;

import com.boogeyman.app.graphql.models.UserProfile;
import com.boogeyman.app.service.UserAccountService;
import com.boogeyman.app.storage.exceptions.UserDataExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserProfileControllerQuery {

    private final UserAccountService accountService;

    @QueryMapping
    public UserProfile userProfileByUserName(@Argument String userName){
        return getUserProfile(userName);
    }

    @QueryMapping
    public UserProfile userProfile(Authentication authentication){
        return getUserProfile(authentication.getName());
    }

    private UserProfile getUserProfile(String userName){
        var acct = accountService.getUserAccount(userName);
        if(acct == null){
            log.error("User is not found!");
            throw new UserDataExistException("User is not found!");
        }

        final UserProfile profile = new UserProfile();
        profile.setAcctId(acct.getAcctId());
        profile.setUserName(userName);
        profile.setEmail(userName);
        return profile;
    }



}
