package com.boogeyman.app.storage.service;

import com.boogeyman.app.storage.entities.AccountUserAddressEntity;
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
public class AccountUserAddressStorageService implements BaseStorageService{

    private static final String CREATE_USER_ADDRESS_STMT = "INSERT INTO APP_USER_ADDRESS(ACCT_ID,STREET1,STREET2,STATE,ZIP,CITY,COUNTRY,ADDRESS_TYPE,CREATED_BY) " +
            "VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String QUERY_USER_ADDRESS_STMT = "SELECT ADDRESS_ID, ACCT_ID,STREET1,STREET2,STATE,ZIP,CITY,COUNTRY,ADDRESS_TYPE,CREATED_BY FROM APP_USER_ADDRESS WHERE ACCT_ID = ?";
    private final JdbcTemplate jdbcTemplate;

    public UUID createUserAddress(AccountUserAddressEntity addressEntity){
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(con -> {
            final PreparedStatement ps = con.prepareStatement(CREATE_USER_ADDRESS_STMT, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, addressEntity.getAcctId());
            ps.setString(2, addressEntity.getStreet1());
            ps.setString(3, addressEntity.getStreet2());
            ps.setString(4, addressEntity.getState());
            ps.setString(5, addressEntity.getZip());
            ps.setString(6, addressEntity.getCity());
            ps.setString(7, addressEntity.getCountry());
            ps.setString(8, addressEntity.getType());
            ps.setString(9, CREATED_BY);
            return ps;
        }, keyHolder);
        final String id = String.valueOf(keyHolder.getKeys().get("ACCT_ID"));
        return UUID.fromString(id);
    }

    public List<AccountUserAddressEntity> getUserAddress(UUID acctId){
        return this.jdbcTemplate
                .query(QUERY_USER_ADDRESS_STMT, ps -> ps.setObject(1, acctId),
                        new AccountUserAddressStorageService.AcctUserAddressRowMapper());
    }

    private static class AcctUserAddressRowMapper implements RowMapper<AccountUserAddressEntity> {
        @Override
        public AccountUserAddressEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            final AccountUserAddressEntity entity = new AccountUserAddressEntity();
            entity.setAddressId(rs.getObject(1, UUID.class));
            entity.setAcctId(rs.getObject(2, UUID.class));
            entity.setStreet1(rs.getString(3));
            entity.setStreet2(rs.getString(4));
            entity.setState(rs.getString(5));
            entity.setZip(rs.getString(6));
            entity.setCity(rs.getString(7));
            entity.setCountry(rs.getString(8));
            entity.setType(rs.getString(9));
            entity.setCreatedBy(rs.getString(10));
            return entity;
        }
    }

}
