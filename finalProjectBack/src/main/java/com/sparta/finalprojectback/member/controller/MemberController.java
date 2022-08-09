package com.sparta.finalprojectback.member.controller;

import com.sparta.finalprojectback.jwt.JwtTokenProvider;
import com.sparta.finalprojectback.member.dto.MemberJoinRequestDto;
import com.sparta.finalprojectback.member.dto.MemberLoginRequestDto;
import com.sparta.finalprojectback.member.dto.MemberResponseDto;
import com.sparta.finalprojectback.member.dto.MemberUpdateRequestDto;
import com.sparta.finalprojectback.member.model.Member;
import com.sparta.finalprojectback.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Api(tags = "회원 기능")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;
    private final JwtTokenProvider jwtTokenProvider;

    @ApiOperation("회원 가입 기능")
    @PostMapping("/join")
    public Long join(@RequestBody MemberJoinRequestDto requestDto) {
        return memberService.join(requestDto);
    }

    @ApiOperation("로그인 기능")
    @PostMapping("/login")
    public String login(@RequestBody MemberLoginRequestDto requestDto) {
        return memberService.login(requestDto);
    }

    //TEST CODE

    @ApiOperation("유저 테스트 기능")
    @GetMapping("user/test")
    public Map userResponseTest(HttpServletRequest request) {
        String token = request.getHeader("X-AUTH-TOKEN");
        String userId = jwtTokenProvider.getUserPk(token);
        Map<String, String> result = new HashMap<>();
        result.put("user ok", userId);
        return result;
    }
    @ApiOperation("관리자 테스트 기능")
    @GetMapping("admin/test")
    public Map adminResponseTest() {
        Map<String, String> result = new HashMap<>();
        result.put("result", "admin ok");
        return result;
    }
    @ApiOperation("나의 정보 가져오는 기능")
    @GetMapping("user")
    public MemberResponseDto myInfo(@AuthenticationPrincipal Member member){
        return memberService.myInfo(member);
    }
    @ApiOperation("모든 유저 보는 관리자 기능")
    @GetMapping("admin/members")
    public ResponseEntity<List<MemberResponseDto>> findUser(@AuthenticationPrincipal Member member){
        return memberService.findUser();
    }
    @ApiOperation("회원 탈퇴 기능")
    @DeleteMapping("user/member")
    public ResponseEntity<String> deleteMe(@AuthenticationPrincipal Member member){
        return memberService.deleteUser(member);
    }

    @ApiOperation("유저 삭제하는 관리자 기능")
    @DeleteMapping("admin/member/{memberId}")
    public ResponseEntity<String> deleteUser(@AuthenticationPrincipal Member member, @PathVariable Long memberId){
        return memberService.deleteUser(member, memberId);
    }

    @ApiOperation("유저 네임 중복 체크 기능")
    @GetMapping("/overlap-username")
    public ResponseEntity<String> findOverlapUsername(@RequestParam String username){
        return memberService.findOverlapUsername(username);
    }
    @ApiOperation("유저 닉네임 중복 체크 기능")
    @GetMapping("/overlap-nickname")
    public ResponseEntity<String> findOverlapNickname(@RequestParam String nickname){
        return memberService.findOverlapNickname(nickname);
    }
    @ApiOperation("유저 이메일 중복 체크 기능")
    @GetMapping("/overlap-email")
    public ResponseEntity<String> findOverlapEmail(@RequestParam String email){
        return memberService.findOverlapEmail(email);
    }
    @ApiOperation("유저 정보 수정하는 기능")
    @PutMapping("user/modify")
    public ResponseEntity<String> modifyUser(@AuthenticationPrincipal Member member, @RequestBody MemberUpdateRequestDto requestDto){
        return memberService.modifyUser(member, requestDto);
    }
    @ApiOperation("유저 비밀번호 확인 기능")
    @GetMapping("user/member")
    public ResponseEntity<String> confirmPassword(@AuthenticationPrincipal Member member, @RequestParam String password){
        return memberService.confirmPassword(member, password);
    }
}
