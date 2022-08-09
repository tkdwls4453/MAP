package com.sparta.finalprojectback.community.repository;

import com.sparta.finalprojectback.community.model.Community;
import com.sparta.finalprojectback.member.model.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {

    List<Community> findAllByOrderByCreatedAtDesc();

    Optional<Community> findById(Long postId);

    List<Community> deleteCommunityByIdAndMember(Long id, Member member);

    List<Community> findAllByMemberId(Long memberId);

    // 페이징
    Page<Community>  findAllByOrderByCreatedAtDesc(Pageable pageable);
}