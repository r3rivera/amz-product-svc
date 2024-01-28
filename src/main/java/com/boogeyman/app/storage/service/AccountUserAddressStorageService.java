package com.boogeyman.app.storage.service;

import com.boogeyman.app.storage.entities.AccountUserAddressEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountUserAddressStorageService implements BaseStorageService{

    private static final String CREATE_USER_ADDRESS_STMT = "INSERT INTO APP_USER_ADDRESS(ACCT_ID,STREET1,STREET2,STATE,ZIP,CITY,COUNTRY,ADDRESS_TYPE,CREATED_BY) " +
            "VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String QUERY_USER_ADDRESS_STMT = "SELECT ACCT_ID,STREET1,STREET2,STATE,ZIP,CITY,COUNTRY,ADDRESS_TYPE,CREATED_BY FROM APP_USER_ADDRESS WHERE ACCT_ID = ?";
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


}
