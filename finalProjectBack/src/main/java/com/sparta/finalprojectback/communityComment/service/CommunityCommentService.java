package com.sparta.finalprojectback.communitycomment.service;

import com.sparta.finalprojectback.community.model.Community;
import com.sparta.finalprojectback.community.repository.CommunityRepository;
import com.sparta.finalprojectback.communitycomment.controller.CommunityCommentController;
import com.sparta.finalprojectback.communitycomment.dto.CommunityCommentRequestDto;
import com.sparta.finalprojectback.communitycomment.dto.CommunityCommentResponseDto;
import com.sparta.finalprojectback.communitycomment.model.CommunityComment;
import com.sparta.finalprojectback.communitycomment.repository.CommunityCommentRepository;
import com.sparta.finalprojectback.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommunityCommentService {
    private final Logger logger = LoggerFactory.getLogger(CommunityCommentController.class);
    private final CommunityCommentRepository communityCommentRepository;
    private final CommunityRepository communityRepository;


    // 댓글 작성
    // 예외처리
    @Transactional
    public Long createComment(Long communityId, CommunityCommentRequestDto requestDto, Member member) {
        Community community = communityRepository.findById(communityId).orElseGet(null);

        if (community == null) {
            throw new NullPointerException("게시물이 존재하지 않습니다.");
        }

        CommunityComment communityComment = new CommunityComment(requestDto, community, member);
        logger.info("createCommentMemberId : {}", member.getId());
        logger.info("createCommentId : {}", communityId);
        return communityCommentRepository.save(communityComment).getId();
    }


     // 조회
    public List<CommunityCommentResponseDto> getCommunityComments(Long communityId) {
        List<CommunityComment> communityCommentList = communityCommentRepository.findAllByCommunity_Id(communityId);
        logger.info("getCommunityCommentsId : {}", communityId);
        return getCommunityCommentResponseDtos(communityCommentList);
    }

    @NotNull
    private List<CommunityCommentResponseDto> getCommunityCommentResponseDtos(List<CommunityComment> communityCommentList) {
        List<CommunityCommentResponseDto> communityCommentResponseDtoList = new ArrayList<>();

        for (CommunityComment communityComment : communityCommentList) {
            communityCommentResponseDtoList.add(new CommunityCommentResponseDto(communityComment.getId(),
                    communityComment.getComment(), communityComment.getMember().getNickname(),
                    communityComment.getCreatedAt(), communityComment.getMember().getImage()));
        }
        return communityCommentResponseDtoList;
    }

    // 삭제
    @Transactional(rollbackFor = Exception.class)
    public void deleteCommunityComment(Long id, Member member) {

        communityCommentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당하는 댓글이 없습니다."));
        logger.info("deleteCommunityCommentMemberId : {}",member.getId());
        logger.info("deleteCommunityCommentId : {}",id);
        communityCommentRepository.deleteByIdAndMember(id, member);
    }


    @Transactional(rollbackFor = Exception.class)
    public void deleteAllCommunityComments(Long id) {
        communityCommentRepository.deleteAllByCommunity_Id(id);
    }
}