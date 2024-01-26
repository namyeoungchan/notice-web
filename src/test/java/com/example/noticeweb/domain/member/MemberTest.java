package com.example.noticeweb.domain.member;

import com.example.noticeweb.util.MemberFixFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemberTest {
    @DisplayName("회원 닉네임 변경")
    @Test
    public void testChangeName() {
        var member = MemberFixFactory.create();
        var toChangeName = "pnu";

        member.changeNickname(toChangeName);
        Assertions.assertEquals(toChangeName,member.getNickname());
    }

    @DisplayName("회원의 닉네임은 10 자를 초과할수 없다,")
    @Test
    public void testNickNameMaxLength() {
        var member = MemberFixFactory.create();
        var overMaxLengthName = "pnupnupnunupnupnupnu";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            member.changeNickname(overMaxLengthName);
        });
    }
}
