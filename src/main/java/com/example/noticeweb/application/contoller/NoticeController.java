package com.example.noticeweb.application.contoller;

import com.example.noticeweb.domain.notice.dto.NoticeDto;
import com.example.noticeweb.domain.notice.dto.WriteNoticeCommand;
import com.example.noticeweb.domain.notice.service.NoticeWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeWriteService noticeService;

    @PostMapping("/notices")
    public NoticeDto createNotice(@RequestBody WriteNoticeCommand noticeDto){
        var notice =  noticeService.createNotice(noticeDto);
        return noticeService.toDto(notice);
    }
    @PostMapping("/notices/{id}")
    public NoticeDto updateNotice(@PathVariable Long id, @RequestBody WriteNoticeCommand noticeDto){
         noticeService.updateNotice(id,noticeDto);
        return noticeService.getNotice(id);
    }

    @GetMapping("/{id}/notices")
    public NoticeDto getNotice(@PathVariable Long id){return noticeService.getNotice(id);
    }
}
