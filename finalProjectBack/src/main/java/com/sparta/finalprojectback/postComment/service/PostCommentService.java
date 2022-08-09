package com.sparta.finalprojectback.postComment.service;

import com.sparta.finalprojectback.member.model.Member;
import com.sparta.finalprojectback.postComment.dto.PostCommentRequestDto;
import com.sparta.finalprojectback.postComment.dto.PostCommentResponseDto;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface PostCommentService {
    ResponseEntity<Long> createPostComment(Member member, PostCommentRequestDto requestDto, Long postId);
    ResponseEntity<String> deletePostComment(Member member, Long commentId);
    ResponseEntity<List<PostCommentResponseDto>> readMyPostComment(Member member, Long postId);
}