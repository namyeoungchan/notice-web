package com.example.noticeweb.domain.notice.repository;

import com.example.noticeweb.domain.member.entiry.Member;
import com.example.noticeweb.domain.notice.dto.NoticeDto;
import com.example.noticeweb.domain.notice.dto.WriteNoticeCommand;
import com.example.noticeweb.domain.notice.entity.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class NoticeRepository {
    static final private String TABLE = "notice";
    final private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Notice createNotice(Notice notice) {
        SimpleJdbcInsert simleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");
        SqlParameterSource params = new BeanPropertySqlParameterSource(notice);
        var id = simleJdbcInsert.executeAndReturnKey(params).longValue();

        return Notice.builder()
                .id(id)
                .title(notice.getTitle())
                .content(notice.getContent())
                .nickname(notice.getNickname())
                .createdAt(notice.getCreatedAt())
                .updatedAt(notice.getUpdatedAt())
                .build();
    }

    public Optional<Notice> findById(Long id) {
        var sql = String.format("SELECT * FROM %s WHERE id = :id",TABLE);
        var param = new MapSqlParameterSource()
                .addValue("id",id);

        RowMapper<Notice> rowMapper= (ResultSet resultSet , int rowNum) -> Notice.builder()
                .id(resultSet.getLong("id"))
                .title(resultSet.getString("title"))
                .content(resultSet.getString("content"))
                .nickname(resultSet.getString("nickname"))
                .createdAt(resultSet.getObject("createdAt",LocalDate.class).atStartOfDay())
                .build();
        var notice = namedParameterJdbcTemplate.queryForObject(sql,param,rowMapper);
        return Optional.ofNullable(notice);
    }
    public Notice save(Notice notice) {
        if(notice.getId() == null){
            return createNotice(notice);
        }
        return updateNotice(notice);
    }

    public Notice updateNotice(Notice notice) {
        var sql = String.format("UPDATE %s SET title = :title, content = :content, nickname = :nickname, updatedAt = :updatedAt WHERE id = :id",TABLE);
        SqlParameterSource params = new BeanPropertySqlParameterSource(notice);
        namedParameterJdbcTemplate.update(sql,params);
        return notice;
    }
}
