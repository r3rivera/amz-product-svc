package com.boogeyman.app.graphql.query;


import com.boogeyman.app.graphql.models.UserProfile;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class UserProfileController {

    @QueryMapping
    public UserProfile userProfile(){
        return UserProfile.builder().firstName("Ryan").lastName("Rivera").build();
    }
}
