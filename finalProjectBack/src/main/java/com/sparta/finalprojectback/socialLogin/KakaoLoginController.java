package com.sparta.finalprojectback.socialLogin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@Api(tags = "카카오 기능")
@RequiredArgsConstructor
@RestController
public class KakaoLoginController {

    private final Logger logger = LoggerFactory.getLogger(KakaoLoginController.class);
    private final KakaoLoginService kakaoLoginService;
    @ApiOperation("카카오 로그인")
    @GetMapping("/kakao/login")
    public String kakaoCallback(@RequestParam String code) {
        logger.info("kakaoCallbackCode : {}", code);
        return kakaoLoginService.kakaoLogin(code);
    }
}