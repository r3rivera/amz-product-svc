package com.boogeyman.app.controller;

import com.boogeyman.app.model.AccountUserAddressRequest;
import com.boogeyman.app.service.UserAddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manage/users")
public class ManagerUserAddressController {

    private final UserAddressService addressService;


    @PostMapping("/{acctId}/address")
    public void createUserAddress(@PathVariable("acctId") UUID id,
                                  @RequestBody AccountUserAddressRequest addressRequest){

    }


}
