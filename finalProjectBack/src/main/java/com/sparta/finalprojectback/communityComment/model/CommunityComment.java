package com.sparta.finalprojectback.communitycomment.model;

import com.sparta.finalprojectback.community.model.Community;
import com.sparta.finalprojectback.communitycomment.dto.CommunityCommentRequestDto;
import com.sparta.finalprojectback.member.model.Member;
import com.sparta.finalprojectback.member.model.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CommunityComment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Community community;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    // 댓글 생성에 이용
    public CommunityComment(CommunityCommentRequestDto requestDto, Community community, Member member) {
        this.comment = requestDto.getComment();
        this.community = community;
        this.member = member;
    }

}