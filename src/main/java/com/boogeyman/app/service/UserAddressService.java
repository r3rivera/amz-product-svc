package com.boogeyman.app.service;


import com.boogeyman.app.graphql.models.UserAddress;
import com.boogeyman.app.graphql.models.types.AddressType;
import com.boogeyman.app.model.AccountUserAddressRequest;
import com.boogeyman.app.storage.entities.AccountUserAddressEntity;
import com.boogeyman.app.storage.service.AccountUserAddressStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAddressService {


    private final AccountUserAddressStorageService addressStorageService;

    public UserAddress getUserAddress(UUID acctId, AddressType type){
        final Optional<UserAddress> address = getUserAddress(acctId).stream()
                .filter(item -> item.getAddressType().equals(type.name())).findFirst();
        if(address.isPresent()){
            return address.get();
        }
        log.warn("Address for {} acctId of type {} is null!", acctId, type);
        return null;
    }

    public UUID createAddress(UUID acctId, AccountUserAddressRequest addressRequest, AddressType type){
        final AccountUserAddressEntity address = new AccountUserAddressEntity();
        address.setStreet1(addressRequest.getStreet1());
        address.setStreet2(addressRequest.getStreet2());
        address.setZip(addressRequest.getZip());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setCountry(addressRequest.getCountry());
        address.setType(type.name());
        address.setAcctId(acctId);
        final UUID addressId = addressStorageService.createUserAddress(address);
        log.info("New address record with {} created!", addressId);
        return addressId;
    }

    @Cacheable("user_address")
    public List<UserAddress> getUserAddress(UUID acctId){
        return this.addressStorageService.getUserAddress(acctId).stream().map(address -> {
           final UserAddress addr = new UserAddress();
           addr.setStreet1(address.getStreet1());
           addr.setStreet2(address.getStreet2());
           addr.setCity(address.getCity());
           addr.setState(address.getState());
           addr.setZip(address.getZip());
           addr.setCountry(address.getCountry());
           addr.setAcctId(address.getAcctId());
           addr.setAddressId(address.getAddressId());
           addr.setAddressType(AddressType.valueOf(address.getType()));
           return  addr;
        }).collect(Collectors.toList());
    }


}
