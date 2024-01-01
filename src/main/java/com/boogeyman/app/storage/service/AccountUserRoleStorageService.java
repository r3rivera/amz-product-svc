package com.boogeyman.app.storage.service;

import com.boogeyman.app.storage.entities.AccountEntity;
import com.boogeyman.app.storage.entities.AccountUserAndRoleEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountUserRoleStorageService implements BaseStorageService {

    private static final String GET_USER_ROLES_QRY_STMT = "SELECT AA.ACCT_ID, RR.ROLE, AA.EMAIL, AA.PASSWORD, " +
            "AA.ACCT_LOCKED, AA.ACTIVE " +
            "FROM APP_ACCOUNT AA LEFT JOIN  APP_ACCOUNT_ROLE RR ON AA.ACCT_ID = RR.ACCT_ID WHERE AA.EMAIL IN (?)";

    private final JdbcTemplate jdbcTemplate;

    public AccountUserAndRoleEntity getUserAuthDetailByEmail(String email){
        if(!StringUtils.hasText(email)){
            log.error("Email is not provided in the request!");
            return null;
        }
        final List<AcctUserRoleItem> users = this.jdbcTemplate
                .query(GET_USER_ROLES_QRY_STMT, ps -> ps.setObject(1, email), new AcctUserRoleEntityRowMapper());

        if(users.isEmpty()){
            return null;
        }
        final List<String> roles = users.stream().map(i -> i.role()).collect(Collectors.toList());
        final AccountEntity user = new AccountEntity();
        user.setAcctId(users.get(0).acct_id());
        user.setEmail(users.get(0).email());
        user.setPassword(users.get(0).password());
        user.setAcctLocked(users.get(0).locked());
        user.setAcctActive(users.get(0).active());
        return new AccountUserAndRoleEntity(user, roles);
    }

}

class AcctUserRoleEntityRowMapper implements RowMapper<AcctUserRoleItem>{
    @Override
    public AcctUserRoleItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AcctUserRoleItem(rs.getObject(1, UUID.class), rs.getString(2),
                rs.getString(3), rs.getString(4),
                rs.getBoolean(5), rs.getBoolean(6)
        );

    }
}

record AcctUserRoleItem(UUID acct_id, String role, String email, String password, boolean locked, boolean active){}
