package com.sparta.finalprojectback.community.service;

import com.sparta.finalprojectback.community.controller.CommunityController;
import com.sparta.finalprojectback.community.dto.CommunityRequestDto;
import com.sparta.finalprojectback.community.dto.CommunityResponseDto;
import com.sparta.finalprojectback.community.model.Community;
import com.sparta.finalprojectback.community.repository.CommunityRepository;
import com.sparta.finalprojectback.member.model.Member;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityService {
    private final Logger logger = LoggerFactory.getLogger(CommunityController.class);
    private final CommunityRepository communityRepository;

    /**
     * 리팩토링 진행중
     */

    /**
     * 예외처리 진행중
     */

    // 게시물 등록
    @Transactional
    public Long createCommunity(CommunityRequestDto requestDto, Member member) {
        Community community = new Community(requestDto, member);
        logger.info("createCommunityMemberId : {}",member.getId());
        return communityRepository.save(community).getId();
    }


    // 조회
    @Transactional(readOnly = true)
    public List<CommunityResponseDto> getCommunityList() {

        // CommunityRepository에서 반환받은건 List<Community>형태
        // .stream().map()을 이용해 ResponseDto로 변환한 후
        // 리스트로 반환

        List<Community> communities = communityRepository.findAllByOrderByCreatedAtDesc();
        return communityRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(CommunityResponseDto::new)
                .collect(Collectors.toList());
    }

    // 페이징처리 (조회)
    public Page<CommunityResponseDto> getAllCommunities(int page, int size, String sortBy, boolean isAsc) {
        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return communityRepository.findAllByOrderByCreatedAtDesc(pageable).map(CommunityResponseDto::new);
    }

    // 나의 게시물 리스트 조회
    public List<CommunityResponseDto> getMyCommunityList(Member member) {
        logger.info("getMyCommunityListMemberId : {}",member.getId());
       return communityRepository.findAllByMemberId(member.getId()).stream()
                .map(CommunityResponseDto::new)
                .collect(Collectors.toList());
    }

    // 특정 게시물 상세조회
    @Transactional
    public CommunityResponseDto communityDetail(Long id) {
        communityRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당하는 게시물이 존재하지 않습니다.")
        );

        CommunityResponseDto communityResponseDto = communityRepository.findById(id).map(CommunityResponseDto::new).get();
        logger.info("communityDetailId : {}",id);
        return communityResponseDto;
    }

    // 수정
    @Transactional(rollbackFor = Exception.class)
    public void updateCommunity(Long id, CommunityRequestDto requestDto, Member member) {
        Community community = communityRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 게시물이 존재하지 않습니다.")
        );
        community.updateCommunity(id, requestDto, member);
        logger.info("updateCommunityMemberId : {}",member.getId());
        logger.info("updateCommunityId : {}",id);
        communityRepository.save(community);
    }

    // 삭제
    @Transactional(rollbackFor = Exception.class)
    public void deleteCommunity(Long id, Member member) {
       communityRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당하는 게시물이 없습니다."));
       logger.info("deleteCommunityMemberId : {}",member.getId());
       logger.info("deleteCommunityId: {} ",id);
        communityRepository.deleteCommunityByIdAndMember(id, member);
    }
}