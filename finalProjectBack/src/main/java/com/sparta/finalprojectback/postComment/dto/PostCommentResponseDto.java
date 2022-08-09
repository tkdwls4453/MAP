package com.sparta.finalprojectback.postComment.dto;

import com.sparta.finalprojectback.member.model.Member;
import com.sparta.finalprojectback.member.model.Timestamped;
import com.sparta.finalprojectback.post.model.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class PostCommentResponseDto extends Timestamped {

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
    private Long id;
    @Column(length = 200)
    private String comment;
    private String member;

    private String img;
    private Long post;
    @Builder
    public PostCommentResponseDto(LocalDateTime createdAt, LocalDateTime modifiedAt, Long id, String comment,Member member,Post post){
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.id = id;
        this.comment = comment;
        this.member = member.getNickname();
        this.post = post.getId();
        this.img = member.getImage();
    }
}
