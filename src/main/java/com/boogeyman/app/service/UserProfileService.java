package com.boogeyman.app.service;

import com.boogeyman.app.graphql.models.*;
import graphql.language.ArrayValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserProfileService {

    private static Map<UUID, UserProfile> tempStore = new HashMap();

    public UProfile createUserProfile(UserProfileRequest request, String email){
        final ContactInfo contactInfo = new ContactInfo(UUID.randomUUID(), ContactType.EMAIL, email);
        final UserProfile.UserProfileBuilder uProfile = UserProfile.builder();
        uProfile.id(UUID.randomUUID())
                .userName(request.getUserName())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(contactInfo);
        final UserProfile u = uProfile.build();
        tempStore.put(u.getId(), u);
        return u;
    }

    public UserProfile getUserProfile(UUID userId){
        if(tempStore.containsKey(userId)){
            return tempStore.get(userId);
        }
        return null;
    }

    public List<UserProfile> getUsers(UserProfileFilter filter){
        if(filter == null){
            return tempStore.values().stream().toList();
        }
        return new ArrayList<>();
    }

}