package com.boogeyman.app.controller;

import com.boogeyman.app.model.AccountUserContactRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manage/users")
public class ManageUserContactController {

    @PostMapping("/contacts")
    public ResponseEntity<Object> createUserContactDetails(Authentication auth,
                                                           @Valid @RequestBody AccountUserContactRequest request){
        log.info("Handling user contact information request!");
        return ResponseEntity.ok().build();
    }


}
