package com.boogeyman.app.storage.service;

import com.boogeyman.app.storage.entities.AccountUserRoleEntity;
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

@Service
public class AccountUserRoleEntityStorageService extends JdbcStorageService<AccountUserRoleEntity, UUID> {

    private static final String CREATE_ACCT_ROLE_STMT = "INSERT INTO APP_ACCOUNT_ROLE(ACCT_ID, ROLE, CREATED_BY) VALUES (?,?,?)";
    private static final String GET_ROLES_BY_ACCT_ID = "SELECT ROLE FROM APP_ACCOUNT_ROLE WHERE ACCT_ID IN (?)";


    protected AccountUserRoleEntityStorageService(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public UUID createRecord(AccountUserRoleEntity record) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(con -> {
            final PreparedStatement ps = con.prepareStatement(CREATE_ACCT_ROLE_STMT, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, record.getAcctId());
            ps.setString(2, record.getRoleName());
            ps.setString(3, CREATED_BY);
            return ps;
        }, keyHolder);
        final String id = String.valueOf(keyHolder.getKeys().get("ACCT_ROLE_ID"));
        return UUID.fromString(id);
    }

    public List<String> getRolesByAcctId(UUID acctId){
        return this.jdbcTemplate.query(GET_ROLES_BY_ACCT_ID,
                ps -> ps.setObject(1, acctId), new AcctRoleMapper());
    }

    private static class AcctRoleMapper implements RowMapper<String>{
        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getString(1);
        }
    }
}
