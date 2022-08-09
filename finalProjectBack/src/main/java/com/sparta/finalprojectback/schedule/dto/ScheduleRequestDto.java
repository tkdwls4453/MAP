package com.sparta.finalprojectback.schedule.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequestDto {
    private Long postId;
    private String placeName;
    private String address;
    private String link;
    private int date;
    private String phone;
    private float x, y;
}
