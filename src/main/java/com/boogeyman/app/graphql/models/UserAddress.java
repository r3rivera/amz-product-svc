package com.boogeyman.app.graphql.models;


import com.boogeyman.app.graphql.models.types.AddressType;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
public class UserAddress {

    private UUID addressId;
    private UUID acctId;

    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private AddressType addressType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAddress address = (UserAddress) o;
        return Objects.equals(addressId, address.addressId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId);
    }
}
