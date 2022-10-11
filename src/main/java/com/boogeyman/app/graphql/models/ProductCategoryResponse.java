package com.boogeyman.app.graphql.models;

import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryResponse {
    private long id;
    private List<Product> products;
    private String category;
}
