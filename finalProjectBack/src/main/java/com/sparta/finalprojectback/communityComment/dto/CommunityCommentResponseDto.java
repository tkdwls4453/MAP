package com.sparta.finalprojectback.communitycomment.dto;

import com.sparta.finalprojectback.member.model.Timestamped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class CommunityCommentResponseDto extends Timestamped {

    private Long commentId;
    private String comment;

    // 댓글 작성자
    private String nickname;

    private String img;

    // 댓글 작성시각

    @CreatedDate
    private LocalDateTime createdAt;

    public CommunityCommentResponseDto(Long commentId, String comment, String nickname, LocalDateTime createdAt, String img) {
        this.commentId = commentId;
        this.comment = comment;
        this.nickname = nickname;
        this.createdAt = createdAt;
        this.img = img;
    }
}