package com.boogeyman.app.storage.service;

import com.boogeyman.app.storage.entities.AccountUserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountUserEntityStorageService implements BaseStorageService{

    private static final String APP_ACCT_USER_STMT = "INSERT INTO APP_ACCT_USER(ACCT_ID, FIRST_NAME,LAST_NAME,CREATED_BY) VALUES (?,?,?,?)";
    private static final String APP_ACCT_USER_QRY = "SELECT ACCT_ID, FIRST_NAME, LAST_NAME FROM APP_ACCT_USER LIMIT ? OFFSET ?";
    private static final String APP_ACCT_USER_BY_ACCT_ID_QRY =  "SELECT ACCT_ID, FIRST_NAME, LAST_NAME FROM APP_ACCT_USER WHERE ACCT_ID = ?";

    private final JdbcTemplate jdbcTemplate;

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

    public List<AccountUserEntity> getAccountUsers(int pageNum, int pageSize){
        final int offset = (pageNum - 1) * pageSize;
        return this.jdbcTemplate.query(APP_ACCT_USER_QRY, ps -> {
            ps.setObject(1, pageSize);
            ps.setObject(2, offset);
        }, new AcctUserRowMapper());
    }

    public AccountUserEntity getAccountUserByAcctId(UUID acctId){
        log.info("Querying DB based on the {} AcctID.", acctId);
        final List<AccountUserEntity> users =  this.jdbcTemplate.query(APP_ACCT_USER_BY_ACCT_ID_QRY, ps -> {
            ps.setObject(1, acctId);
        }, new AcctUserRowMapper());
        if(users.isEmpty()){
            log.info("No record found for {} acctId!", acctId);
            return null;
        }
        return users.get(0);
    }


    private static class AcctUserRowMapper implements RowMapper<AccountUserEntity>{
        @Override
        public AccountUserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            final AccountUserEntity entity = new AccountUserEntity();
            entity.setAcctUserId(rs.getObject(1, UUID.class));
            entity.setFirstName(rs.getString(2));
            entity.setLastName(rs.getString(3));
            return entity;
        }
    }

}