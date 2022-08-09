package com.sparta.finalprojectback.postComment.service;

import com.sparta.finalprojectback.postComment.controller.PostCommentController;
import com.sparta.finalprojectback.postComment.model.PostComment;
import com.sparta.finalprojectback.postComment.repository.PostCommentRepository;
import com.sparta.finalprojectback.member.model.Member;
import com.sparta.finalprojectback.post.model.Post;
import com.sparta.finalprojectback.post.repository.PostRepository;
import com.sparta.finalprojectback.postComment.dto.PostCommentRequestDto;
import com.sparta.finalprojectback.postComment.dto.PostCommentResponseDto;
import com.sparta.finalprojectback.statuscode.ResponseMessage;
import com.sparta.finalprojectback.statuscode.StatusCode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostCommentServicelmpl implements PostCommentService {

    private final Logger logger = LoggerFactory.getLogger(PostCommentController.class);
    private final PostCommentRepository postCommentRepository;
    private final PostRepository postRepository;

    @Override
    public ResponseEntity<Long> createPostComment(Member member, PostCommentRequestDto requestDto, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("PostId를 찾을수 없음")
        );
        PostComment postComment;
        if (requestDto.getComment() == null) throw new NullPointerException("값을 입력해주세요");
        else if (requestDto.getComment().length() > 200) throw new RuntimeException("More than 200 words");
        else {
            postComment = new PostComment(requestDto.getComment(), post, member);
        }
        postCommentRepository.save(postComment);
        logger.info("createPostCommentMemberId : {}",member.getId());
        logger.info("createPostCommentPostId : {}",postId);
        return new ResponseEntity<>(postComment.getId(),HttpStatus.valueOf(StatusCode.CREATED));
    }

    @Override
    public ResponseEntity<String> deletePostComment(Member member, Long commentId) {
        postCommentRepository.deleteById(commentId);
        logger.info("deletePostCommentMemberId : {}",member.getId());
        logger.info("deletePostCommentCommentId : {}",commentId);
        return new ResponseEntity<>(ResponseMessage.DELETE_POST_COMMENT, HttpStatus.valueOf(StatusCode.OK));
    }

    @Override
    public ResponseEntity<List<PostCommentResponseDto>> readMyPostComment(Member member, Long postId) {
        List<PostComment> postComments = postCommentRepository.findAllByPost_Id(postId);
        List<PostCommentResponseDto> postCommentsList = new ArrayList<>();
        for (PostComment postComment : postComments){
            postCommentsList.add(
                    PostCommentResponseDto.builder()
                            .createdAt(postComment.getCreatedAt())
                            .modifiedAt(postComment.getModifiedAt())
                            .id(postComment.getId())
                            .comment(postComment.getComment())
                            .member(postComment.getMember())
                            .post(postComment.getPost())
                            .build());
        }
        logger.info("readMyPostCommentMemberId : {}", member.getId());
        logger.info("readMyPostCommentPostId : {}", postId);
        return new ResponseEntity<>(postCommentsList, HttpStatus.valueOf(StatusCode.OK));
    }
}
