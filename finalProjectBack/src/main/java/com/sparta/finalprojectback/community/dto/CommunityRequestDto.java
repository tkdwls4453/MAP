package com.sparta.finalprojectback.community.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CommunityRequestDto {

    @Size(max = 45, message = "제목은 45자 이하로 작성해주세요")
    private String title;
    @Size(max = 300, message = "내용은 300자 이하로 작성해주세요")
    private String content;
}