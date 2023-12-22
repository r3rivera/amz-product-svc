package com.boogeyman.app.storage.service;

import com.boogeyman.app.storage.entities.AccountEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.UUID;

@Service
public class AccountEntityStorageService extends JdbcStorageService<AccountEntity, UUID> {

    private static final String INSERT_ACCT_STMT = "INSERT INTO APP_ACCOUNT(EMAIL, PASSWORD, CREATED_BY) VALUES (?,?,?)";
    protected AccountEntityStorageService(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public UUID createRecord(AccountEntity entity) {

        final KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(con -> {
            final PreparedStatement ps = con.prepareStatement(INSERT_ACCT_STMT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, entity.getEmail());
            ps.setString(2, entity.getPassword());
            ps.setString(3, CREATED_BY);
            return ps;
        }, keyHolder);
        final String id = String.valueOf(keyHolder.getKeys().get("ACCT_ID"));
        return UUID.fromString(id);
    }
}
