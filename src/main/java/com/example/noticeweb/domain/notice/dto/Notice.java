package com.example.noticeweb.domain.notice.dto;

public record Notice(Long id,
                     String title,
                     String content,
                     Long memberId,
                     String nickname,
                     String createdAt,
                     String updatedAt) {
}
