package com.boogeyman.app.graphql.controller;


import com.boogeyman.app.graphql.models.UserProfile;
import com.boogeyman.app.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserProfileQuery {

    private final UserProfileService userProfileService;

    @QueryMapping
    public UserProfile userProfile(){
        return null;
    }

    @QueryMapping
    public UserProfile userProfileBy(@Argument String id){
        return this.userProfileService.getUserProfile(UUID.fromString(id));
    }
}
