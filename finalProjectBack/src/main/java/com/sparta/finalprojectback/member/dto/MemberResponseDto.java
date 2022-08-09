package com.sparta.finalprojectback.member.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberResponseDto{
    private Long id;
    private String username;
    private String email;
    private String nickname;
    private String image;
    private LocalDateTime createdAt;
}
