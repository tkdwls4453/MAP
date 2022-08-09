package com.sparta.finalprojectback.communitycomment.repository;

import com.sparta.finalprojectback.communitycomment.model.CommunityComment;
import com.sparta.finalprojectback.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityCommentRepository extends JpaRepository<CommunityComment, Long> {

    List<CommunityComment> findAllByCommunity_Id(Long id);

    List<CommunityComment> deleteByIdAndMember(Long id, Member member);

    List<CommunityComment> deleteAllByCommunity_Id(Long id);
}