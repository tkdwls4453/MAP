package com.sparta.finalprojectback.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class MemberJoinRequestDto {

    private String username;
    private String password;
    private String nickname;
    private String email;
}
