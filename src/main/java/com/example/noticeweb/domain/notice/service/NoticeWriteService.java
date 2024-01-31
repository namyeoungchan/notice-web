package com.example.noticeweb.domain.notice.service;

import com.example.noticeweb.domain.notice.dto.NoticeDto;
import com.example.noticeweb.domain.notice.dto.WriteNoticeCommand;
import com.example.noticeweb.domain.notice.entity.Notice;
import com.example.noticeweb.domain.notice.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return noticeRepository.save(notice);
    }

    public NoticeDto getNotice(Long id) {
        var notice = noticeRepository.findById(id).orElseThrow();
        return toDto(notice);
    }


    public NoticeDto toDto(Notice notice){
        return new NoticeDto(notice.getId(),notice.getTitle(),notice.getContent(), notice.getNickname(),notice.getCreatedAt(),notice.getUpdatedAt());
    }

    public void updateNotice(Long id, WriteNoticeCommand noticeDto) {
        var notice = noticeRepository.findById(id).orElseThrow();
        notice.update(noticeDto);
        noticeRepository.save(notice);
    }

    public Notice deleteNotice(Long id) {
        var notice = noticeRepository.findById(id).orElseThrow();
        return noticeRepository.delete(notice);
    }

}
