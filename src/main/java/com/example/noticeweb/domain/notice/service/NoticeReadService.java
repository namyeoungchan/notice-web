package com.example.noticeweb.domain.notice.service;


import com.example.noticeweb.domain.notice.dto.NoticeDto;
import com.example.noticeweb.domain.notice.entity.Notice;
import com.example.noticeweb.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoticeReadService {
    private final NoticeRepository noticeRepository;

    public List<NoticeDto> getNotices(Long pageNum) {
        return noticeRepository.findAll(pageNum)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public NoticeDto toDto(Notice notice){
        return new NoticeDto(notice.getId(),notice.getTitle(),notice.getContent(), notice.getNickname(),notice.getCreatedAt(),notice.getUpdatedAt());
    }

}
