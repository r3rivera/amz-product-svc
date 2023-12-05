package com.boogeyman.app.graphql.models;

import lombok.Data;

@Data
public class Product{
    private String name;
    private String brand;
    private String description;
    private double amount;
    private int quantity;
}
