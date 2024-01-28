package com.boogeyman.app.storage.entities;

import lombok.Data;

import java.util.UUID;

@Data
public class AccountUserAddressEntity extends BaseEntity{

    private UUID addressId;
    private UUID acctId;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String type;

}
