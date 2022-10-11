package com.boogeyman.app.graphql.resolvers;

import com.boogeyman.app.graphql.models.Product;
import com.boogeyman.app.graphql.models.ProductCategoryResponse;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductCategoryResolver implements GraphQLResolver<ProductCategoryResponse> {

    public List<Product> getProducts(ProductCategoryResponse response, String filterBy){
        log.info("PayloadResponseResolver.getProducts :: Response ID :: " + response.getId());
        Product p1 = new Product();
        p1.setName("Coke");
        p1.setBrand("Coca-cola");
        p1.setAmount(0.89);
        p1.setQuantity(10);

        Product p2 = new Product();
        p2.setName("Sprite");
        p2.setBrand("Coca-cola");
        p2.setAmount(0.65);
        p2.setQuantity(25);

        Product p3 = new Product();
        p3.setName("Mountain Dew");
        p3.setBrand("Pepsi");
        p3.setAmount(0.25);
        p3.setQuantity(30);

        Product p4 = new Product();
        p4.setName("Pepsi");
        p4.setBrand("Pepsi");
        p4.setAmount(0.70);
        p4.setQuantity(12);

        if(!StringUtils.hasText(filterBy) && "".equalsIgnoreCase(filterBy)){
            return Arrays.asList(p1, p2, p3, p4);
        }

        log.info("Done getting the products");
        return Arrays.asList(p1, p2, p3, p4).stream().filter( i -> filterBy.equalsIgnoreCase(i.getBrand())).collect(Collectors.toList());
    }

    public String getCategory(ProductCategoryResponse response){
        log.info("PayloadResponseResolver.getCategory :: Response ID :: " + response.getId());
        return "Drinks";
    }

}
