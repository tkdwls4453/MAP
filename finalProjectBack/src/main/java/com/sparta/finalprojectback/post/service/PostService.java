package com.sparta.finalprojectback.post.service;

import com.sparta.finalprojectback.member.model.Member;
import com.sparta.finalprojectback.post.dto.PostResponseDto;
import com.sparta.finalprojectback.post.dto.PostRequestDto;
import com.sparta.finalprojectback.post.model.Post;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface PostService {

    ResponseEntity<Long> createPost(Member member);
    ResponseEntity<String> deletePost(Member member, Long postId);
    ResponseEntity<String> updatePost(Member member, Long postId, PostRequestDto requestDto);
    ResponseEntity<List<PostResponseDto>> readMyPost(Member member, boolean isComplete);
    ResponseEntity<String> deleteEmptyPost(Member member, boolean isComplete);
    ResponseEntity<List<PostResponseDto>> readAllPost(boolean isComplete);
    // 이미지 업로드 api는 나중에 진행
    Post getPostById(Long postId);
    ResponseEntity<PostResponseDto> readPostInfo(Long postId);
    ResponseEntity<PostResponseDto> countingView(Long postId);
}
