package com.boogeyman.app.security.service;

import com.boogeyman.app.storage.entities.AccountEntity;
import com.boogeyman.app.storage.entities.AccountUserAndRoleEntity;
import com.boogeyman.app.storage.service.AccountEntityStorageService;

import com.boogeyman.app.storage.service.AccountUserRoleStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthUserDetailStoreManager implements UserDetailsManager {

    private final PasswordEncoder encoder;
    private final AccountEntityStorageService entityStorageService;
    private final AccountUserRoleStorageService userRoleStorageService;

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String email) {
        final AccountEntity entity = this.entityStorageService.getRecordByEmail(email);
        if(entity == null){
            return false;
        }
        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final AccountUserAndRoleEntity user = userRoleStorageService.getUserAuthDetailByEmail(username);
        if(user == null){
            log.error("{} is not found!", username);
            throw new UsernameNotFoundException("User is not found");
        }

        final UserDetails acct = User.builder()
                .username(user.accountEntity().getEmail())
                .password(this.encoder.encode(user.accountEntity().getPassword()))
                .roles(user.roleEntity().toArray( new String[0]))
                .accountExpired(false)
                .accountLocked(user.accountEntity().isAcctLocked())
                .credentialsExpired(false)
                .disabled(!user.accountEntity().isAcctActive()).build();
        return acct;
    }
}
