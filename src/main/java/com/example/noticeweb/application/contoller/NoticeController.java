package com.example.noticeweb.application.contoller;

import com.example.noticeweb.domain.notice.dto.NoticeDto;
import com.example.noticeweb.domain.notice.dto.WriteNoticeCommand;
import com.example.noticeweb.domain.notice.service.NoticeReadService;
import com.example.noticeweb.domain.notice.service.NoticeWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class NoticeController {
    private final NoticeWriteService noticeWriteService;
    private final NoticeReadService noticeReadService;

    @PostMapping("/notices")
    public NoticeDto createNotice(@RequestBody WriteNoticeCommand noticeDto){
        var notice =  noticeWriteService.createNotice(noticeDto);
        return noticeWriteService.toDto(notice);
    }
    @PutMapping("/notices/{id}")
    public NoticeDto updateNotice(@PathVariable Long id, @RequestBody WriteNoticeCommand noticeDto){
         noticeWriteService.updateNotice(id,noticeDto);
        return noticeWriteService.getNotice(id);
    }

    @GetMapping("/{id}/notices")
    public NoticeDto getNotice(@PathVariable Long id){return noticeWriteService.getNotice(id);
    }
    @GetMapping("/notices/{pageNum}")
    public List<NoticeDto> getNotices(@PathVariable Long pageNum) {
        return noticeReadService.getNotices(pageNum);
    }

    @DeleteMapping("/{id}/notices")
    public NoticeDto deleteNotice(@PathVariable Long id){
       return noticeWriteService.toDto(noticeWriteService.deleteNotice(id));
    }
}
