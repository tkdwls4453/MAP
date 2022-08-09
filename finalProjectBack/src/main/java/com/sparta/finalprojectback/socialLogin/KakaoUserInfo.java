package com.sparta.finalprojectback.socialLogin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KakaoUserInfo {
    Long kakaoId;
    String email;
    String nickname;
}