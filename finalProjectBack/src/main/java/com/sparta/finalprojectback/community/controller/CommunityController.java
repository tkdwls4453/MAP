package com.sparta.finalprojectback.community.controller;

import com.sparta.finalprojectback.community.dto.CommunityResponseDto;
import com.sparta.finalprojectback.community.dto.CommunityRequestDto;
import com.sparta.finalprojectback.community.service.CommunityService;
import com.sparta.finalprojectback.communitycomment.service.CommunityCommentService;
import com.sparta.finalprojectback.member.model.Member;
import com.sparta.finalprojectback.statuscode.ResponseMessage;
import com.sparta.finalprojectback.statuscode.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "커뮤니티 게시물 기능")
@RequiredArgsConstructor
@RestController
public class CommunityController {
    private final CommunityService communityService;
    private final CommunityCommentService communityCommentService;

    /**
     * 리팩토링 진행중
     */
    @ApiOperation("커뮤니티 게시물 작성")
    @PostMapping("/user/community/post")
    public Long createCommunity(@RequestBody @Valid CommunityRequestDto requestDto, @AuthenticationPrincipal Member member) {
        return communityService.createCommunity(requestDto, member);
    }
    @ApiOperation("커뮤니티 게시물 전체 조회")
    @GetMapping("/user/community/posts")
    public List<CommunityResponseDto> getCommunityList() {
        return communityService.getCommunityList();
    }
    @ApiOperation("커뮤니티 게시물 페이징 처리")
    @GetMapping("/user/community/posts/paging")
    public Page<CommunityResponseDto> getAllCommunities(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc
    ) {
        return communityService.getAllCommunities(page, size, sortBy, isAsc);
    }
    @ApiOperation("내가 작성한 커뮤니티 게시물 조회")
    @GetMapping("/user/community/my-posts")
    public List<CommunityResponseDto> getMyCommunityList(@AuthenticationPrincipal Member member) {
        return communityService.getMyCommunityList(member);
    }
    @ApiOperation("하나의 커뮤니티 게시물 조회")
    @GetMapping("/user/community/post/{postId}")
    public ResponseEntity<CommunityResponseDto> getCommunityDetail(@PathVariable Long postId) {
        return new ResponseEntity<>(communityService.communityDetail(postId), HttpStatus.valueOf(StatusCode.OK));
    }
    @ApiOperation("커뮤니티 게시물 수정")
    @PutMapping("/user/community/post/{postId}")
    public ResponseEntity<String> updateCommunity(@PathVariable Long postId, @RequestBody CommunityRequestDto requestDto, @AuthenticationPrincipal Member member) {
        communityService.updateCommunity(postId, requestDto, member);
        return new ResponseEntity<>(ResponseMessage.UPDATE_POST, HttpStatus.valueOf(StatusCode.OK));
    }

    /**
     * 리팩토링 (연관관계 매핑)
     */
    @ApiOperation("커뮤니티 게시물 삭제")
    @DeleteMapping("/user/community/post/{postId}")
    public ResponseEntity<String> deleteCommunity(@PathVariable Long postId, @AuthenticationPrincipal Member member) {
//        communityCommentService.deleteAllCommunityComments(postId);
        communityService.deleteCommunity(postId, member);
        return new ResponseEntity<>(ResponseMessage.DELETE_POST, HttpStatus.valueOf(StatusCode.OK));
    }
}

