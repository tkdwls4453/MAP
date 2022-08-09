package com.sparta.finalprojectback.communitycomment.controller;

import com.sparta.finalprojectback.communitycomment.dto.CommunityCommentRequestDto;
import com.sparta.finalprojectback.communitycomment.dto.CommunityCommentResponseDto;
import com.sparta.finalprojectback.communitycomment.service.CommunityCommentService;
import com.sparta.finalprojectback.member.model.Member;
import com.sparta.finalprojectback.statuscode.ResponseMessage;
import com.sparta.finalprojectback.statuscode.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "커뮤니티 댓글 기능")
@RestController
@RequiredArgsConstructor
public class CommunityCommentController {
    private final CommunityCommentService communityCommentService;
    @ApiOperation("커뮤니티 댓글 작성")
    @PostMapping("/user/community/post/{postId}/comment")
    public ResponseEntity<Long> createCommunityComment(@PathVariable Long postId,
                                                       @RequestBody CommunityCommentRequestDto requestDto,
                                                       @AuthenticationPrincipal Member member) {


        return new ResponseEntity<>(communityCommentService.createComment(postId, requestDto, member), HttpStatus.valueOf(StatusCode.OK));
    }
    @ApiOperation("커뮤니티 댓글 조회")
    @GetMapping("/user/community/post/{postId}/comment")
    public ResponseEntity<List<CommunityCommentResponseDto>> getCommunityCommentList(@PathVariable Long postId) {
        return new ResponseEntity<>(communityCommentService.getCommunityComments(postId), HttpStatus.valueOf(StatusCode.OK));
    }
    @ApiOperation("커뮤니티 댓글 삭제")
    @DeleteMapping("/user/community/post/comment/{commentId}")
    public ResponseEntity<String> deleteCommunityComment(@PathVariable Long commentId, @AuthenticationPrincipal Member member) {
        communityCommentService.deleteCommunityComment(commentId, member);
        return new ResponseEntity<>(ResponseMessage.DELETE_POST_COMMENT, HttpStatus.valueOf(StatusCode.OK));
    }
}