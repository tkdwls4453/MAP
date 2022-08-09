package com.sparta.finalprojectback.post.controller;

import com.sparta.finalprojectback.member.model.Member;
import com.sparta.finalprojectback.post.dto.PostResponseDto;
import com.sparta.finalprojectback.post.dto.PostRequestDto;
import com.sparta.finalprojectback.post.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "여행 게시물 기능")
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @ApiOperation("여행 게시물 생성 기능")
    @PostMapping("/user/plan/post")
    public ResponseEntity<Long> createPost(@AuthenticationPrincipal Member member){
        return postService.createPost(member);
    }
    @ApiOperation("나의 여행 게시물 삭제 기능")
    @DeleteMapping("/user/plan/post/{postId}")
    public ResponseEntity<String> deletePost(@AuthenticationPrincipal Member member, @PathVariable Long postId){
        return postService.deletePost(member, postId);
    }
    @ApiOperation("나의 여행 게시물 수정 기능")
    @PutMapping("/user/plan/post/{postId}")
    public ResponseEntity<String> updatePost(@AuthenticationPrincipal Member member, @PathVariable Long postId, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(member, postId, requestDto);
    }
    @ApiOperation("나의 여행 게시물 조회 기능")
    @GetMapping("/user/plan/my-posts")
    public ResponseEntity<List<PostResponseDto>> readMyPost(@AuthenticationPrincipal Member member, boolean isComplete){
        postService.deleteEmptyPost(member, isComplete);
        return postService.readMyPost(member, isComplete);
    }
    @ApiOperation("모든 여행 게시물 조회 기능")
    @GetMapping("/plan/posts")
    public ResponseEntity<List<PostResponseDto>> readAllPost(boolean isComplete){
        return postService.readAllPost(isComplete);
    }
    @ApiOperation("여행 게시물 정보 가져오는 기능")
    @GetMapping("/plan/post/{postId}")
    public ResponseEntity<PostResponseDto> readPostInfo(@PathVariable Long postId){
        return postService.readPostInfo(postId);
    }
    @ApiOperation("게시물 조회 수 증가 시키는 기능")
    @PutMapping("/user/plan/post/{postId}/view")
    public ResponseEntity<PostResponseDto> countingView(@PathVariable Long postId){
        return postService.countingView(postId);
    }
    @ApiOperation("actuator health 체크 기능")
    @GetMapping("/actuator/health") public ResponseEntity<String> actuator(){
        return new ResponseEntity("success", HttpStatus.OK); }
}
