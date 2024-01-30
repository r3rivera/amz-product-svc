package com.boogeyman.app.model;

import lombok.Data;

@Data
public class AppError {
    private String message;
    private Object invalidValue;
    private String type;
    private Object path;
}
