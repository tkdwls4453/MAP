package com.sparta.finalprojectback.postComment.controller;

import com.sparta.finalprojectback.postComment.dto.PostCommentRequestDto;
import com.sparta.finalprojectback.postComment.dto.PostCommentResponseDto;
import com.sparta.finalprojectback.postComment.service.PostCommentService;
import com.sparta.finalprojectback.member.model.Member;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "게시물 댓글 기능")
@RestController
@RequiredArgsConstructor
public class PostCommentController {
    private final PostCommentService postCommentService;
    @ApiOperation("게시물 댓글 생성 기능")
    @PostMapping("/user/plan/post/{postId}/comment")
    public ResponseEntity<Long> createPostComment(@AuthenticationPrincipal Member member, @PathVariable Long postId, @RequestBody PostCommentRequestDto requestDto){
        return postCommentService.createPostComment(member, requestDto, postId);
    }
    @ApiOperation("게시물 댓글 불러오는 기능")
    @GetMapping("/plan/post/{postId}/comment")
    public ResponseEntity<List<PostCommentResponseDto>> readPostComment(@AuthenticationPrincipal Member member, @PathVariable Long postId){
        return postCommentService.readMyPostComment(member, postId);
    }
    @ApiOperation("게시물 댓글 삭제하는 기능")
    @DeleteMapping("/user/plan/post/comment/{commentId}")
    public ResponseEntity<String> deletePostComment(@AuthenticationPrincipal Member member, @PathVariable Long commentId){
        return postCommentService.deletePostComment(member, commentId);
    }
}
