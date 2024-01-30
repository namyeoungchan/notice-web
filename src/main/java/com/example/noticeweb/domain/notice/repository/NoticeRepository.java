package com.example.noticeweb.domain.notice.repository;

import com.example.noticeweb.domain.notice.dto.NoticeDto;
import com.example.noticeweb.domain.notice.dto.WriteNoticeCommand;
import com.example.noticeweb.domain.notice.entity.Notice;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class NoticeRepository {
    static final private String TABLE = "Notice";
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
}
