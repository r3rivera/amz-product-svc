package com.boogeyman.app.model;

import jakarta.validation.constraints.Max;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
public class ProductRequest {

    @Max(10)
    private int quantity;
}
