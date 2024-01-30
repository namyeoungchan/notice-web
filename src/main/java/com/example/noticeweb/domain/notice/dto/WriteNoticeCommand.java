package com.example.noticeweb.domain.notice.dto;

public record WriteNoticeCommand(
        Long id,
        String title,
        String content,
        String nickname
) {
}
