package com.boogeyman.app.graphql.models;


import com.boogeyman.app.graphql.models.types.AddressType;
import lombok.Data;

import java.util.Objects;
import java.util.UUID;

@Data
public class UserAddress {

    private UUID acctId;
    private String street1;
    private String street2;
    private String city;
    private String zip;
    private String country;
    private AddressType addressType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAddress address = (UserAddress) o;
        return Objects.equals(acctId, address.acctId) && Objects.equals(street1, address.street1) && Objects.equals(street2, address.street2) && Objects.equals(city, address.city) && Objects.equals(zip, address.zip) && Objects.equals(country, address.country) && addressType == address.addressType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(acctId, street1, street2, city, zip, country, addressType);
    }
}
