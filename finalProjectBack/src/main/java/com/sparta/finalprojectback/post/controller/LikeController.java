package com.sparta.finalprojectback.post.controller;

import com.sparta.finalprojectback.member.model.Member;
import com.sparta.finalprojectback.post.dto.LikeResponseDto;
import com.sparta.finalprojectback.post.service.LikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "좋아요 기능")
@RequiredArgsConstructor
@RestController
public class LikeController {
    private final LikeService likeService;
    @ApiOperation("좋아요/취소 기능")
    @PostMapping("/user/plan/post/{postId}/like")
    public Long createLike(@AuthenticationPrincipal Member member, @PathVariable Long postId) {
        return likeService.isLike(member, postId);
    }
    @ApiOperation("내가 좋아요 한 게시물 불러오기")
    @GetMapping("/user/plan/posts/my-like")
    public List<LikeResponseDto> getMyLikes(@AuthenticationPrincipal Member member) {
        return likeService.getMyLikes(member);
    }
}