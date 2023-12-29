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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "AppDBTxnManager")
public class UserAccountService {

    private final AccountEntityStorageService entityStorageService;
    private final AccountUserEntityStorageService userEntityStorageService;
    private final AccountUserRoleEntityStorageService userRoleEntityStorageService;

    public UUID createUserAccount(AccountUserRequest request){

        final AccountEntity existUser = entityStorageService.getRecordByEmail(request.getEmail());
        if(existUser != null){
            log.error("User already exist!");
            throw new UserDataExistException();
        }

        final AccountEntity accountEntity = new AccountEntity();
        accountEntity.setEmail(request.getEmail());
        accountEntity.setPassword(request.getPassword());

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
