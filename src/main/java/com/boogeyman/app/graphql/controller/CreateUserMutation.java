package com.boogeyman.app.graphql.controller;

import com.boogeyman.app.graphql.models.UProfile;
import com.boogeyman.app.graphql.models.UserProfile;
import com.boogeyman.app.graphql.models.UserProfileEmailRequest;
import com.boogeyman.app.graphql.models.UserProfileRequest;
import com.boogeyman.app.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CreateUserMutation {

    private final UserProfileService userProfileService;

    @MutationMapping
    public UProfile createUser(@Validated @Argument UserProfileRequest user){
        final UserProfile.UserProfileBuilder uProfile = UserProfile.builder();
        uProfile.id(UUID.randomUUID()).userName(user.getUserName()).firstName(user.getFirstName()).lastName(user.getLastName());
        return uProfile.build();
    }

    @MutationMapping
    public UProfile createUserEmail(@Argument UserProfileEmailRequest request){
        final UserProfileRequest user = request.getProfile();
        final String emailAdd = request.getEmail();
        return this.userProfileService.createUserProfile(user, emailAdd);
    }

}
