package com.sparta.finalprojectback.socialLogin;

import com.sparta.finalprojectback.jwt.JwtTokenProvider;
import com.sparta.finalprojectback.member.model.Member;
import com.sparta.finalprojectback.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KakaoLoginService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final KakaoOAuth kakaoOAuth;

    @Transactional
    public String kakaoLogin(String authorizedCode) {
        // 카카오 OAuth2 를 통해 카카오 사용자 정보 조회
        System.out.println("authorizedCode: " + authorizedCode);

        KakaoUserInfo userInfo = kakaoOAuth.getUserInfo(authorizedCode);
        String kakaoId = Long.toString(userInfo.getKakaoId());   // kakaoId를 String을 변환시키기
        String email = "kakao";

        Optional<Member> member = memberRepository.findByUsername(kakaoId);

        String jwt = "";

        Member savedMember = null;
        // 이미 가입한 카카오 회원이라면 정보로 토큰 발급
        if (member.isEmpty()) {
            savedMember = memberRepository.save(Member.builder()
                    .username(kakaoId)
                    .password(passwordEncoder.encode(UUID.randomUUID().toString()))
                    .nickname(userInfo.getNickname())
                    .email(email)
                    .roles(Collections.singletonList("ROLE_USER"))
                    .build());
        }else {
            savedMember = member.get();
        }
        jwt = jwtTokenProvider.createToken(savedMember.getUsername(),
                savedMember.getRoles());

        return jwt;
    }
}