package com.sparta.finalprojectback.member.service;

import com.sparta.finalprojectback.member.dto.MemberUpdateRequestDto;
import com.sparta.finalprojectback.member.dto.MemberJoinRequestDto;
import com.sparta.finalprojectback.member.dto.MemberLoginRequestDto;
import com.sparta.finalprojectback.member.dto.MemberResponseDto;
import com.sparta.finalprojectback.member.model.Member;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MemberService {

    public Long join(MemberJoinRequestDto requestDto);
    public String login(MemberLoginRequestDto requestDto);
    public MemberResponseDto myInfo(Member member);
    ResponseEntity<List<MemberResponseDto>> findUser();
    ResponseEntity<String> deleteUser(Member member, Long memberId);
    ResponseEntity<String> deleteUser(Member member);
    ResponseEntity<String> findOverlapUsername(String username);
    ResponseEntity<String> findOverlapNickname(String nickname);
    ResponseEntity<String> findOverlapEmail(String email);
    ResponseEntity<String> modifyUser(Member member, MemberUpdateRequestDto requestDto);

    ResponseEntity<String> confirmPassword(Member member, String password);
}
