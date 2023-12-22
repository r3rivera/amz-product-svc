package com.boogeyman.app.controller;

import com.boogeyman.app.model.AccountUserRequest;
import com.boogeyman.app.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manage/account")
public class ManageUserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping("/user")
    public ResponseEntity<UUID> createUser(@RequestBody @Validated AccountUserRequest request){
        final UUID acctId = this.userAccountService.createUserAccount(request);
        if(acctId != null){
            return ResponseEntity.ok(acctId);
        }
        return ResponseEntity.badRequest().build();
    }
}
