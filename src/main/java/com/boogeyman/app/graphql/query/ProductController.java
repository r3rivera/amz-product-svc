package com.boogeyman.app.graphql.query;

import com.boogeyman.app.graphql.models.Product;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ProductController {

    @QueryMapping
    public String greetings(){
        return "test";
    }

    @QueryMapping
    public Product productById(){
        final Product p = new Product();
        p.setAmount(23);
        p.setName("Reese");
        p.setBrand("Chocolate");
        p.setQuantity(2);
        p.setDescription("Some Delicious");
        return p;
    }


    @SchemaMapping
    public String description(Product p){
        return "What Food";
    }

}
