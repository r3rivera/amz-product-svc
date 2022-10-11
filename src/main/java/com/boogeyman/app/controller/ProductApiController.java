package com.boogeyman.app.controller;


import com.boogeyman.app.model.ProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/products-api")
public class ProductApiController {

    @PostMapping("/product")
    public ResponseEntity<String> addProduct(@RequestBody ProductRequest request){
        log.info("Adding new product :: " + request);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
