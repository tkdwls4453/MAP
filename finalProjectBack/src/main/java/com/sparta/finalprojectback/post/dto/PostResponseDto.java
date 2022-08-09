package com.sparta.finalprojectback.post.dto;

import com.sparta.finalprojectback.post.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class PostResponseDto {
    
    private Long id;

    private String title;

    private String content;

    private Category category;

    private String nickname;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    private int period;

    private String image;

    private int likes;

    private int views;
}
