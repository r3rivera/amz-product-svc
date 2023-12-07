package com.boogeyman.app.graphql.models;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ContactInfo {
    private UUID id;
    private ContactType category;
    private String contactValue;
}
