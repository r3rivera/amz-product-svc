package com.boogeyman.app.graphql.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Address {
    private UUID id;
    private String street1;
    private String street2;
    private String city;
    private String zip;
    private String country;
}
