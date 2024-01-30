package com.boogeyman.app.storage.service;

import com.boogeyman.app.storage.entities.AccountScheduleEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
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
public class AccountUserScheduleStorageService implements BaseStorageService{

    private static final String CREATE_USER_SCHEDULE_STMT = "INSERT INTO APP_SCHEDULE(ACCT_ID,TITLE,DESCRIPTION,LOCATION,BLOCKED,START_DT,END_DT,CREATED_BY) " +
            "VALUES (?,?,?,?,?,?,?,?)";
    private static final String QUERY_USER_SCHEDULE_STMT = "SELECT ACCT_ID,TITLE,DESCRIPTION,LOCATION,BLOCKED,START_DT,END_DT,CREATED_BY FROM APP_SCHEDULE " +
            "WHERE ACCT_ID = ?";

    private final JdbcTemplate jdbcTemplate;

    public UUID createScheduleRecord(AccountScheduleEntity record) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcTemplate.update(con -> {
            final PreparedStatement ps = con.prepareStatement(CREATE_USER_SCHEDULE_STMT, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, record.getAcctId());
            ps.setString(2, record.getTitle());
            ps.setString(3, record.getDescription());
            ps.setString(4, record.getLocation());
            ps.setBoolean(5, record.isBlocked());
            ps.setTimestamp(6, Timestamp.valueOf(record.getStartDate()));
            ps.setTimestamp(7, Timestamp.valueOf(record.getEndDate()));
            ps.setString(8, CREATED_BY);
            return ps;
        }, keyHolder);
        final String id = String.valueOf(keyHolder.getKeys().get("ACCT_ID"));
        return UUID.fromString(id);
    }

    public List<AccountScheduleEntity> getUserSchedules(UUID acctId){
        return this.jdbcTemplate
                .query(QUERY_USER_SCHEDULE_STMT, ps -> ps.setObject(1, acctId),
                        new AccountUserScheduleStorageService.AcctUserScheduleRowMapper());
    }

    private static class AcctUserScheduleRowMapper implements RowMapper<AccountScheduleEntity> {
        @Override
        public AccountScheduleEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            final AccountScheduleEntity entity = new AccountScheduleEntity();
            entity.setAcctId(rs.getObject(1, UUID.class));
            entity.setTitle(rs.getString(2));
            entity.setDescription(rs.getString(3));
            entity.setLocation(rs.getString(4));
            entity.setBlocked(rs.getBoolean(5));

            entity.setStartDate(rs.getTimestamp(6).toLocalDateTime());
            entity.setEndDate(rs.getTimestamp(7).toLocalDateTime());
            entity.setCreatedBy(rs.getString(8));
            return entity;
        }
    }



}
