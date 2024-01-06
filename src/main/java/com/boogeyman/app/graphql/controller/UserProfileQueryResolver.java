package com.boogeyman.app.graphql.controller;

import com.boogeyman.app.graphql.models.UserProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class UserProfileQueryResolver {

    @SchemaMapping
    public String firstName(UserProfile profile){
        log.info("Resolving firstName attribute!");
        return "GGFIRST";
    }

    @SchemaMapping
    public String lastName(UserProfile profile){
        log.info("Resolving lastName attribute!");
        return "GGLAST";
    }

    @SchemaMapping
    public String email(UserProfile profile){
        log.info("Resolving email attribute!");
        return profile.getEmail();
    }
}
