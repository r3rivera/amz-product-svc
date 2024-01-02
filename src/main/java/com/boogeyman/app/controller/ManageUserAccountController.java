package com.boogeyman.app.controller;

import com.boogeyman.app.model.AccountUserList;
import com.boogeyman.app.model.AccountUserRequest;
import com.boogeyman.app.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ManageUserAccountController {

    private final UserAccountService userAccountService;

    @PostMapping("/app/user")
    public ResponseEntity<UUID> createUser(@RequestBody @Validated AccountUserRequest request){
        final UUID acctId = this.userAccountService.createUserAccount(request);
        if(acctId != null){
            return ResponseEntity.ok(acctId);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/manage/users")
    public ResponseEntity<AccountUserList> getUserList(){
        final AccountUserList list = this.userAccountService.getUserAccountList(1, 20);
        if(list != null){
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/manage/users")
    public ResponseEntity updateUserPassword(Authentication authentication, @RequestBody UserPasswordUpdateRequest acctUserPassword){
        this.userAccountService.updateUserPassword(authentication.getName(), acctUserPassword.newPassword());
        return ResponseEntity.ok().build();
    }
    
    public ResponseEntity updateUserRoles(Authentication authentication, @RequestBody AcctUserRoleUpdateRequest roleUpdateRequest){
        return ResponseEntity.noContent().build();
    }

    record UserPasswordUpdateRequest(String newPassword){ }
    record AcctUserRoleUpdateRequest(List<String> roles){}

}
