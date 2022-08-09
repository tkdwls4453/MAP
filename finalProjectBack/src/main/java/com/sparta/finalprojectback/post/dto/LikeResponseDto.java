package com.sparta.finalprojectback.post.dto;

import com.sparta.finalprojectback.post.model.Likes;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class LikeResponseDto {

    private Long memberId;
    private Long postId;

    public LikeResponseDto(Likes like) {
        this.memberId = like.getMember().getId();
        this.postId = like.getPost().getId();
    }

    public static List<LikeResponseDto> getLikeList(List<Likes> likes) {
        return likes.stream()
                .map(LikeResponseDto::new)
                .collect(Collectors.toList());
    }

}
