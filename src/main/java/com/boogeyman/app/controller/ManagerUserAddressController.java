package com.boogeyman.app.controller;

import com.boogeyman.app.exceptions.RequestProcessException;
import com.boogeyman.app.graphql.models.types.AddressType;
import com.boogeyman.app.model.AccountUserAddressRequest;
import com.boogeyman.app.service.UserAccountService;
import com.boogeyman.app.service.UserAddressService;
import com.boogeyman.app.storage.entities.AccountEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manage/users")
public class ManagerUserAddressController {

    private final UserAddressService addressService;
    private final UserAccountService userAccountService;

    @PostMapping("/{acctId}/address/home")
    public void createUserHomeAddress(@PathVariable("acctId") UUID acctId,
                                  @RequestBody AccountUserAddressRequest addressRequest){
        final UUID addressId = addressService.createAddress(acctId, addressRequest, AddressType.RESIDENTIAL);
    }

    @PostMapping("/{acctId}/address/work")
    public void createUserWorkAddress(@PathVariable("acctId") UUID acctId,
                                      @RequestBody AccountUserAddressRequest addressRequest){
        final UUID addressId = addressService.createAddress(acctId, addressRequest, AddressType.WORK);
    }

    @PostMapping("/address/home")
    public void createUserHomeAddressByUsername(Authentication authentication, @RequestBody AccountUserAddressRequest addressRequest){
        createUserAddress(authentication, addressRequest, AddressType.RESIDENTIAL);
    }

    @PostMapping("/address/work")
    public void createUserWorkAddressByUsername(Authentication authentication,@RequestBody AccountUserAddressRequest addressRequest){
        createUserAddress(authentication, addressRequest, AddressType.WORK);
    }

    private UUID createUserAddress(Authentication auth, AccountUserAddressRequest addressRequest, AddressType type){
        final Optional<AccountEntity> accountEntity = userAccountService.getUserAccount(auth.getName());
        if(accountEntity.isPresent()){
            final UUID acctId = accountEntity.get().getAcctId();
            return addressService.createAddress(acctId, addressRequest, type);
        }
        log.error("Unable to proceed! No user found associated to the address being created!");
        throw new RequestProcessException("User is missing!");
    }




}
