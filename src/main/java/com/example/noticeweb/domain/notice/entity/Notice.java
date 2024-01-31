package com.example.noticeweb.domain.notice.entity;

import com.example.noticeweb.domain.notice.dto.WriteNoticeCommand;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Getter
public class Notice {
    final private Long id;
    private String title;
    private String content;
    private String nickname;
    final private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public Notice(Long id, String title, String content, String nickname, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = Objects.requireNonNull(title);
        this.content = Objects.requireNonNull(content);
        this.nickname = Objects.requireNonNull(nickname);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
        this.updatedAt = updatedAt == null ? LocalDateTime.now() : updatedAt;
    }

    public void update(WriteNoticeCommand noticeDto) {
        this.title = noticeDto.title();
        this.content = noticeDto.content();
        this.nickname = noticeDto.nickname();
        this.updatedAt = LocalDateTime.now();
    }
}
