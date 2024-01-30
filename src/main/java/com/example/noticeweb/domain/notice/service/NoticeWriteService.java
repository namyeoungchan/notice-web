package com.example.noticeweb.domain.notice.service;

import com.example.noticeweb.domain.notice.dto.WriteNoticeCommand;
import com.example.noticeweb.domain.notice.entity.Notice;
import com.example.noticeweb.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoticeWriteService {
    private final NoticeRepository noticeRepository;


    public Notice createNotice(WriteNoticeCommand noticeDto) {

        var notice = Notice.builder()
                .title(noticeDto.title())
                .content(noticeDto.content())
                .nickname(noticeDto.nickname())
                .build();
        return noticeRepository.createNotice(notice);
    }
}
