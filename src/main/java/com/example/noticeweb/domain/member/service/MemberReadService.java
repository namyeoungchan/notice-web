package com.example.noticeweb.domain.member.service;


import com.example.noticeweb.domain.member.dto.MemberDto;
import com.example.noticeweb.domain.member.entiry.Member;
import com.example.noticeweb.domain.member.entiry.MemberNicknameHistoryDto;
import com.example.noticeweb.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.noticeweb.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberReadService {
    final private MemberRepository memberRepository;
    final private MemberNicknameHistoryRepository memberNicknameHistoryRepository;
    public MemberDto getMember(Long id) {
        var member = memberRepository.findById(id).orElseThrow();
        return toDto(member);
    }

    public List<MemberNicknameHistoryDto> getNicknameHistories(Long memberId){
        return memberNicknameHistoryRepository
                .findAllByMemberId(memberId)
                .stream()
                .map(this::toDto).toList();
    }

    public MemberDto toDto(Member member) {
        return new MemberDto(member.getId(),member.getEmail(),member.getNickname(),member.getBirthday());
    }

    private MemberNicknameHistoryDto toDto(MemberNicknameHistoryDto history){
        return new MemberNicknameHistoryDto(history.getId(),history.getMemberId(),history.getNickname(),history.getCreatedAt());
    }
}
