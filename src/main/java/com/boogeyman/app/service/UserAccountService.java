package com.boogeyman.app.service;

import com.boogeyman.app.model.AccountUserList;
import com.boogeyman.app.model.AccountUserRequest;
import com.boogeyman.app.model.AccountUserResult;
import com.boogeyman.app.storage.entities.AccountEntity;
import com.boogeyman.app.storage.entities.AccountUserEntity;
import com.boogeyman.app.storage.entities.AccountUserRoleEntity;
import com.boogeyman.app.storage.exceptions.DataStoreException;
import com.boogeyman.app.storage.exceptions.UserDataExistException;
import com.boogeyman.app.storage.service.AccountEntityStorageService;
import com.boogeyman.app.storage.service.AccountUserEntityStorageService;
import com.boogeyman.app.storage.service.AccountUserRoleEntityStorageService;
import com.boogeyman.app.types.AcctRoleTypes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "AppDBTxnManager")
public class UserAccountService {

    private final PasswordEncoder encoder;
    private final AccountEntityStorageService entityStorageService;
    private final AccountUserEntityStorageService userEntityStorageService;
    private final AccountUserRoleEntityStorageService userRoleEntityStorageService;

    public UUID createUserAccount(AccountUserRequest request){

        if(getUserAccount(request.getEmail()).isEmpty()){
            log.error("User already exist!");
            throw new UserDataExistException();
        }

        final AccountEntity accountEntity = new AccountEntity();
        accountEntity.setEmail(request.getEmail());
        accountEntity.setPassword(encoder.encode(request.getPassword()));

        final UUID acctId = entityStorageService.createRecord(accountEntity);
        final AccountUserEntity accountUserEntity = new AccountUserEntity();
        accountUserEntity.setAcctId(acctId);
        accountUserEntity.setFirstName(request.getFirstName());
        accountUserEntity.setLastName(request.getLastName());

        final AccountUserRoleEntity roleEntity = new AccountUserRoleEntity();
        roleEntity.setAcctId(acctId);
        roleEntity.setRoleName(AcctRoleTypes.GENERIC_USER.name());

        final UUID acctRoleId = userRoleEntityStorageService.createRecord(roleEntity);
        final UUID acctUserId = userEntityStorageService.createRecord(accountUserEntity);
        if(acctUserId == null || acctRoleId == null){
            log.error("Unable to create the record!");
            throw new DataStoreException("Error with record!");
        }
        return acctId;
    }

    public Optional<AccountEntity> getUserAccount(String userName){
        final AccountEntity entity = entityStorageService.getRecordByEmail(userName);
        if(entity != null){
            return Optional.of(entity);
        }
        return Optional.empty();

    }


    @Cacheable("user_profile")
    public AccountUserEntity getUserByAcctId(UUID acctId){
        log.info("User_profile is null, Using the database.");
        return  this.userEntityStorageService.getAccountUserByAcctId(acctId);
    }

    public void updateUserPassword(String userName, String newPassword){
        final AccountEntity existUser = entityStorageService.getRecordByEmail(userName);
        if(existUser == null){
            log.error("User name does not exist for update password!");
            throw new UserDataExistException();
        }

        if(encoder.matches(newPassword, existUser.getPassword())){
            log.error("Password matches the old password!");
            throw new DataStoreException("Old password matches with new one");
        }else{
            final String newPasswordEnc = encoder.encode(newPassword);
            if(!entityStorageService.updateUserPassword(existUser.getAcctId(), userName, newPasswordEnc)){
                throw new DataStoreException("Unable to update password");
            }
        }
    }

    public void updateUserRoles(String userName, List<String> roles){

    }

    public AccountUserList getUserAccountList(int pageNum, int pageSize){
        final List<AccountUserEntity> userEntityList = userEntityStorageService.getAccountUsers(pageNum, pageSize);
        if(userEntityList.isEmpty()){
            return null;
        }
        final List<AccountUserResult> userRequestList = new ArrayList<>();
        for(AccountUserEntity e: userEntityList){
            final AccountUserResult user =
                    new AccountUserResult(e.getAcctUserId(), e.getFirstName(), e.getLastName());
            userRequestList.add(user);
        }
        return new AccountUserList(userRequestList);
    }
}
