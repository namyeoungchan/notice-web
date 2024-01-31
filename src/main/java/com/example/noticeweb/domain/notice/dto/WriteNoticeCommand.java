package com.example.noticeweb.domain.notice.dto;

public record WriteNoticeCommand(
        String title,
        String content,
        String nickname
) {
}
