package com.boogeyman.app.service;

import com.boogeyman.app.model.AccountUserRequest;
import com.boogeyman.app.storage.entities.AccountEntity;
import com.boogeyman.app.storage.entities.AccountUserEntity;
import com.boogeyman.app.storage.exceptions.DataStoreException;
import com.boogeyman.app.storage.service.AccountEntityStorageService;
import com.boogeyman.app.storage.service.AccountUserEntityStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(transactionManager = "AppDBTxnManager")
public class UserAccountService {

    private final AccountEntityStorageService entityStorageService;
    private final AccountUserEntityStorageService userEntityStorageService;

    public UUID createUserAccount(AccountUserRequest request){

        final AccountEntity accountEntity = new AccountEntity();
        accountEntity.setEmail(request.getEmail());
        accountEntity.setPassword(request.getPassword());


        final UUID acctId = entityStorageService.createRecord(accountEntity);
        final AccountUserEntity accountUserEntity = new AccountUserEntity();
        accountUserEntity.setAcctId(acctId);
        accountUserEntity.setFirstName(request.getFirstName());
        accountUserEntity.setLastName(request.getLastName());

        final UUID acctUserId = userEntityStorageService.createRecord(accountUserEntity);
        if(acctUserId == null){
            log.error("Unable to create the record!");
            throw new DataStoreException("Error with record!");
        }
        return acctId;
    }
}
