package com.sparta.finalprojectback.post.service;

import com.sparta.finalprojectback.member.model.Member;
import com.sparta.finalprojectback.post.controller.LikeController;
import com.sparta.finalprojectback.post.dto.LikeResponseDto;
import com.sparta.finalprojectback.post.model.Likes;
import com.sparta.finalprojectback.post.model.Post;
import com.sparta.finalprojectback.post.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final Logger logger = LoggerFactory.getLogger(LikeController.class);
    private final LikeRepository likeRepository;
    private final PostService postService;

    // 좋아요
    @Transactional
    public Long isLike(Member member, Long postId) {

        Optional<Likes> likePost = likeRepository.findByMemberIdAndPostId(member.getId(), postId);

        if (likePost.isEmpty()) {
            createLike(member, postId);
            logger.info("isLikeMemberId : {}", member.getId());
            logger.info("isLikePostId : {}", postId);
            likeRepository.findByMemberIdAndPostId(member.getId(), postId).get().getPost().updateLike(1);
        } else {
            logger.info("unLikeMemberId : {}", member.getId());
            logger.info("unLikePostId : {}", postId);
            likeRepository.findByMemberIdAndPostId(member.getId(), postId).get().getPost().updateLike(-1);
            likeRepository.deleteByMemberIdAndPostId(member.getId(), postId);
        }
        return postId;
    }

    // 좋아요 생성 (Likes DB에 넣어줌)
    public Likes createLike(Member member, Long postId) {

        Post post = postService.getPostById(postId);
        Likes like = new Likes(member, post);

        return likeRepository.save(like);
    }


    // 내가 좋아요 한 게시물 불러오기
    public List<LikeResponseDto> getMyLikes(Member member) {
        return likeRepository.findAllByMemberId(member.getId()).stream()
                .map(LikeResponseDto::new)
                .collect(Collectors.toList());
    }

}