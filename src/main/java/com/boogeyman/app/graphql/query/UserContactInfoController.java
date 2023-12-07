package com.boogeyman.app.graphql.query;

import com.boogeyman.app.graphql.models.ContactInfo;
import com.boogeyman.app.graphql.models.ContactType;
import com.boogeyman.app.graphql.models.UserProfile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Slf4j
@Controller
public class UserContactInfoController {

    @QueryMapping
    public UserProfile userContactInfo(){
        log.info("UserContactInfo Query triggered!");
        return UserProfile.builder().id(UUID.randomUUID()).firstName("R2").build();
    }

    @SchemaMapping
    public ContactInfo email(UserProfile profile){
        log.info("Email field triggered! for [{}] ", profile.getId());
        return new ContactInfo(UUID.randomUUID(), ContactType.EMAIL, "abc@test.com");
    }

    @SchemaMapping
    public ContactInfo phone(UserProfile profile){
        log.info("Phone field triggered! for [{}] ", profile.getId());
        return new ContactInfo(UUID.randomUUID(), ContactType.SMS, "88888888");
    }

}
