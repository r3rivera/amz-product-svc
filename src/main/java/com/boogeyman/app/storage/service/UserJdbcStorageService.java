package com.boogeyman.app.storage.service;

import com.boogeyman.app.storage.entities.AppUserEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserJdbcStorageService extends JdbcStorageService<AppUserEntity, Boolean>{

    private static String CREATE_USER_QUERY = "insert into app_user (username,first_name,last_name) values (?,?,?)";
    protected UserJdbcStorageService(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public Boolean createRecord(AppUserEntity record) {
        final int result = this.jdbcTemplate.update(CREATE_USER_QUERY,
                record.getUserName(), record.getFirstName(), record.getLastName());
        return result == 1;
    }
}
