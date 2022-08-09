package com.sparta.finalprojectback.member.service;

import com.sparta.finalprojectback.jwt.JwtTokenProvider;
import com.sparta.finalprojectback.member.dto.MemberUpdateRequestDto;
import com.sparta.finalprojectback.member.controller.MemberController;
import com.sparta.finalprojectback.member.dto.MemberJoinRequestDto;
import com.sparta.finalprojectback.member.dto.MemberLoginRequestDto;
import com.sparta.finalprojectback.member.dto.MemberResponseDto;
import com.sparta.finalprojectback.member.model.Member;
import com.sparta.finalprojectback.member.repository.MemberRepository;
import com.sparta.finalprojectback.statuscode.ResponseMessage;
import com.sparta.finalprojectback.statuscode.StatusCode;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final Logger logger = LoggerFactory.getLogger(MemberController.class);
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    @Override
    public Long join(MemberJoinRequestDto requestDto) {
        logger.info("joinUsername : {}", requestDto.getUsername());
        return memberRepository.save(Member.builder()
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .nickname(requestDto.getNickname())
                .email(requestDto.getEmail())
                .roles(Collections.singletonList("ROLE_USER"))
                .build()).getId();
    }

    @Override
    public String login(MemberLoginRequestDto requestDto) {
        System.out.println(requestDto.getPassword());
        System.out.println(requestDto.getUsername());
        Member member = memberRepository.findByUsername(requestDto.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("가입되지 않은 아이디 입니다.")
        );
        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        logger.info("loginId : {}", member.getId());
        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }

    @Override
    public MemberResponseDto myInfo(Member member) {
        logger.info("myInfoId : {}", member.getId());
        return MemberResponseDto.builder()
                .id(member.getId())
                .username(member.getUsername())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .createdAt(member.getCreatedAt())
                .image(member.getImage())
                .build();
    }

    @Override
    public ResponseEntity<List<MemberResponseDto>> findUser() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>();
        for (Member member : members){
            memberResponseDtoList.add(MemberResponseDto.builder()
                    .id(member.getId())
                    .username(member.getUsername())
                    .email(member.getEmail())
                    .nickname(member.getNickname())
                    .createdAt(member.getCreatedAt())
                    .build()
            );
        }
        return new ResponseEntity<>(memberResponseDtoList, HttpStatus.valueOf(StatusCode.OK));
    }
    @Override
    public ResponseEntity<String> deleteUser(Member member, Long memberId) {
        memberRepository.deleteById(memberId);
        logger.info("deleteUserId : {}", memberId);
        return new ResponseEntity<>(ResponseMessage.DELETE_USER, HttpStatus.valueOf(StatusCode.OK));
    }

    @Override
    public ResponseEntity<String> deleteUser(Member member) {
        memberRepository.deleteById(member.getId());
        return new ResponseEntity<>(ResponseMessage.DELETE_USER, HttpStatus.valueOf(StatusCode.OK));
    }

    @SneakyThrows
    @Override
    public ResponseEntity<String> findOverlapUsername(String username) {
        Member member = memberRepository.findByUsername(username).orElse(new Member());
        if (username.equals(member.getUsername())) {
            return new ResponseEntity<>(ResponseMessage.READ_FIND_USERNAME, HttpStatus.valueOf(StatusCode.OK));
        }
        return new ResponseEntity<>(HttpStatus.valueOf(StatusCode.OK));
    }
    @SneakyThrows
    @Override
    public ResponseEntity<String> findOverlapNickname(String nickname) {
        Member member = memberRepository.findByNickname(nickname).orElse(new Member());
        if (nickname.equals(member.getNickname())) {
            return new ResponseEntity<>(ResponseMessage.READ_FIND_NICKNAME, HttpStatus.valueOf(StatusCode.OK));
        }
        return new ResponseEntity<>(HttpStatus.valueOf(StatusCode.OK));
    }
    @SneakyThrows
    @Override
    public ResponseEntity<String> findOverlapEmail(String email) {
        Member member = memberRepository.findByEmail(email).orElse(new Member());
        if (email.equals(member.getEmail())) {
            return new ResponseEntity<>(ResponseMessage.READ_FIND_EMAIL, HttpStatus.valueOf(StatusCode.OK));
        }
        return new ResponseEntity<>(HttpStatus.valueOf(StatusCode.OK));
    }
    @SneakyThrows
    @Override
    @Transactional
    public ResponseEntity<String> modifyUser(Member member, MemberUpdateRequestDto requestDto){
        Member memberModify = memberRepository.findById(member.getId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저입니다.")
        );
        memberModify.updateUser(requestDto);
        return new ResponseEntity<>(ResponseMessage.UPDATE_USER, HttpStatus.valueOf(StatusCode.OK));
    }

    @Override
    @SneakyThrows
    public ResponseEntity<String> confirmPassword(Member member, String password) {
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return new ResponseEntity<>("비밀번호 확인이 완료되었습니다.", HttpStatus.valueOf(StatusCode.OK));
    }
}