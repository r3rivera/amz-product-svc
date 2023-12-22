package com.boogeyman.app.storage.service;

import com.boogeyman.app.storage.entities.AccountUserEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.UUID;

@Service
public class AccountUserEntityStorageService extends JdbcStorageService<AccountUserEntity, UUID> {

    private static final String APP_ACCT_USER_STMT = "INSERT INTO APP_ACCT_USER(ACCT_ID, FIRST_NAME,LAST_NAME,CREATED_BY) VALUES (?,?,?,?)";
    protected AccountUserEntityStorageService(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public UUID createRecord(AccountUserEntity entity) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(con -> {
            final PreparedStatement ps = con.prepareStatement(APP_ACCT_USER_STMT, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, entity.getAcctId());
            ps.setString(2, entity.getFirstName());
            ps.setString(3, entity.getLastName());
            ps.setString(4, CREATED_BY);
            return ps;
        }, keyHolder);
        final String id = String.valueOf(keyHolder.getKeys().get("ACCT_USERID"));
        return UUID.fromString(id);
    }

}