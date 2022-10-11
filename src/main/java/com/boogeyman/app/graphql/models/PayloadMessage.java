package com.boogeyman.app.graphql.models;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public @Data class PayloadMessage {
    private String content;
    private String sender;
}
