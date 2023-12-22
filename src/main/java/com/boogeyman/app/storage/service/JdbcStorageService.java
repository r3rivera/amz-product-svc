package com.boogeyman.app.storage.service;

import org.springframework.jdbc.core.JdbcTemplate;

public abstract class JdbcStorageService<I, R> implements StorageService<I, R>, BaseStorageService{
    protected final JdbcTemplate jdbcTemplate;

    protected JdbcStorageService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
