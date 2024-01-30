package com.example.noticeweb.application.contoller;

import com.example.noticeweb.domain.notice.dto.NoticeDto;
import com.example.noticeweb.domain.notice.dto.WriteNoticeCommand;
import com.example.noticeweb.domain.notice.entity.Notice;
import com.example.noticeweb.domain.notice.service.NoticeWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/notices")
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeWriteService noticeService;

    @PostMapping("/")
    public NoticeDto createNotice(@RequestBody WriteNoticeCommand noticeDto){
        var notice =  noticeService.createNotice(noticeDto);
        return toDto(notice);
    }
    private NoticeDto toDto(Notice notice){
        return new NoticeDto(notice.getId(),notice.getTitle(),notice.getContent(), notice.getNickname(),notice.getCreatedAt(),notice.getUpdatedAt());
    }
}
