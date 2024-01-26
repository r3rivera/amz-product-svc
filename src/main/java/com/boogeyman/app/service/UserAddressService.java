package com.boogeyman.app.service;


import com.boogeyman.app.graphql.models.UserAddress;
import com.boogeyman.app.graphql.models.types.AddressType;
import com.boogeyman.app.model.AccountUserAddressRequest;
import com.boogeyman.app.storage.entities.AccountUserAddressEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserAddressService {


    public UserAddress getUserAddress(UUID acctId, AddressType type){
        final Optional<UserAddress> address = getUserAddress(acctId).stream()
                .filter(item -> item.getAddressType().equals(type.name())).findFirst();
        if(address.isPresent()){
            return address.get();
        }
        log.warn("Address for {} acctId of type {} is null!", acctId, type);
        return null;
    }

    public void createAddress(UUID acctId, AccountUserAddressRequest addressRequest, AddressType type){
        final AccountUserAddressEntity address = new AccountUserAddressEntity();
        address.setStreet1(addressRequest.getStreet1());
        address.setStreet2(addressRequest.getStreet2());
        address.setZip(addressRequest.getZip());
        address.setCity(addressRequest.getCity());
        address.setCountry(addressRequest.getCountry());
        address.setType(type.name());
        address.setAcctId(acctId);


    }

    @Cacheable("user_address")
    public List<UserAddress> getUserAddress(UUID acctId){


        return new ArrayList<>();
    }


}
