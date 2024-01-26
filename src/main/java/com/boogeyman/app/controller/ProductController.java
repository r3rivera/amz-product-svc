package com.boogeyman.app.controller;

import com.boogeyman.app.model.ProductRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {


    @PostMapping("/manage/products")
    public boolean addProduct(@Valid @RequestBody ProductRequest request){
        log.info("Adding new products! Quantity is {}", request.getQuantity());
        return true;
    }

}
