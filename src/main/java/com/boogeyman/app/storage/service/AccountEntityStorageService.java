package com.boogeyman.app.storage.service;

import com.boogeyman.app.storage.entities.AccountEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountEntityStorageService implements BaseStorageService{

    private static final String INSERT_ACCT_STMT = "INSERT INTO APP_ACCOUNT(EMAIL, PASSWORD, CREATED_BY) VALUES (?,?,?)";
    private static final String GET_ACCT_BY_EMAIL = "SELECT ACCT_ID, EMAIL, PASSWORD, ACCT_LOCKED, ACTIVE, CREATED_BY FROM APP_ACCOUNT WHERE EMAIL IN (?)";

    private static final String UPDATE_PASSWORD_BY_EMAIL_STMT = "UPDATE APP_ACCOUNT SET PASSWORD=?, MODIFIED_BY=?, MODIFIED_DATE=? WHERE ACCT_ID = ?";

    private final JdbcTemplate jdbcTemplate;

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


    public AccountEntity getRecordByEmail(String email){
        final List<AccountEntity> users = this.jdbcTemplate
                .query(GET_ACCT_BY_EMAIL, ps -> ps.setObject(1, email), new AcctUserRowMapper());
        if(users.isEmpty()){
            return null;
        }
        return users.get(0);
    }


    public boolean updateUserPassword(UUID acctId, String modifiedBy, String newPassword){
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        final int result = this.jdbcTemplate.update(con -> {
            final PreparedStatement ps = con.prepareStatement(UPDATE_PASSWORD_BY_EMAIL_STMT,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, newPassword);
            ps.setString(2, modifiedBy);
            ps.setObject(3, LocalDate.now());
            ps.setObject(4, acctId);
            return ps;
        }, keyHolder);
        if(result == 1){
            return true;
        }
        return false;
    }


    private static class AcctUserRowMapper implements RowMapper<AccountEntity> {
        @Override
        public AccountEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            final AccountEntity entity = new AccountEntity();
            entity.setAcctId(rs.getObject(1, UUID.class));
            entity.setEmail(rs.getString(2));
            entity.setPassword(rs.getString(3));
            entity.setAcctLocked(rs.getBoolean(4));
            entity.setAcctActive(rs.getBoolean(5));
            entity.setCreatedBy(rs.getString(6));
            return entity;
        }
    }
}
