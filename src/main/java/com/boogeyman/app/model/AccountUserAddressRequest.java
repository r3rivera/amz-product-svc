package com.boogeyman.app.model;

import com.boogeyman.app.graphql.models.types.AddressType;
import lombok.Data;

@Data
public class AccountUserAddressRequest {
    private String street1;
    private String street2;
    private String city;
    private String zip;
    private String country;
    private AddressType addressType;
}
