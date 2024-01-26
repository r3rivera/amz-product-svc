package com.boogeyman.app.graphql.controller;

import com.boogeyman.app.graphql.models.UserAddress;
import com.boogeyman.app.service.UserAddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserAddressQueryResolver {

    private final UserAddressService addressService;

    @SchemaMapping
    public String street1(UserAddress userAddress){
        return "1800 Main Street";
    }

    @SchemaMapping
    public String street2(UserAddress userAddress){
        return "Suite 16";
    }

    @SchemaMapping
    public String city(UserAddress userAddress){
        return "Anna";
    }

    @SchemaMapping
    public String zip(UserAddress userAddress){
        return "75127";
    }
    @SchemaMapping
    public String country(UserAddress userAddress){
        return "USA";
    }

}
