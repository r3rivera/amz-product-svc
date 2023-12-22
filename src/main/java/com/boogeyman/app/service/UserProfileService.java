package com.boogeyman.app.service;

import com.boogeyman.app.graphql.models.*;
import com.boogeyman.app.storage.entities.AppUserEntity;
import com.boogeyman.app.storage.service.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final StorageService<AppUserEntity, Boolean> storageService;

    public UProfile createUserProfile(UserProfileRequest request, String email){
        final AppUserEntity entity = new AppUserEntity();
        entity.setUserName(request.getUserName());
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());

        final boolean result = storageService.createRecord(entity);
        UserProfile u = null;
        if(result){
            final ContactInfo contactInfo = new ContactInfo(UUID.randomUUID(), ContactType.EMAIL, email);
            UserProfile.UserProfileBuilder uProfile = UserProfile.builder();
            uProfile.id(UUID.randomUUID())
                    .userName(request.getUserName())
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .email(contactInfo);
            u = uProfile.build();
        }
        return u;
    }

    public UserProfile getUserProfile(UUID userId){
        return null;
    }

    public List<UserProfile> getUsers(UserProfileFilter filter){
        return new ArrayList<>();
    }

}
