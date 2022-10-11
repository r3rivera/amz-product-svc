package com.boogeyman.app.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode(exclude = "name")
public class ProductRequest {
    private String barcode;
    private String name;


}
