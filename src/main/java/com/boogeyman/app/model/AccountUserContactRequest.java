package com.boogeyman.app.model;

import com.boogeyman.app.constraints.PhoneNumber;
import com.boogeyman.app.graphql.models.types.AddressType;
import lombok.Data;

@Data
public class AccountUserContactRequest {

    private AddressType contactType;

    @PhoneNumber
    private String phoneNumber;
}
