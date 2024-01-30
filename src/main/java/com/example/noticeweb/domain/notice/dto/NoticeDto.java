package com.example.noticeweb.domain.notice.dto;

import java.time.LocalDateTime;

public record NoticeDto(Long id,
                        String title,
                        String content,
                        String nickname,
                        LocalDateTime createdAt,
                        LocalDateTime updatedAt) {
}
