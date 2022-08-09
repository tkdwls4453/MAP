package com.sparta.finalprojectback.schedule.controller;

import com.sparta.finalprojectback.post.dto.PostResponseDto;
import com.sparta.finalprojectback.schedule.dto.ScheduleRequestDto;
import com.sparta.finalprojectback.schedule.dto.ScheduleResponseDto;
import com.sparta.finalprojectback.schedule.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "일정 기능")
@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    @ApiOperation("일정 생성 기능")
    @PostMapping("user/schedule")
    ResponseEntity<Long> createSchedule(@RequestBody ScheduleRequestDto requestDto){
        return scheduleService.createSchedule(requestDto);
    }
    @ApiOperation("일정 조회 기능")
    @GetMapping("plan/post/{postId}/schedules")
    ResponseEntity<List<ScheduleResponseDto>> readSchedule(@PathVariable Long postId){
        return scheduleService.readSchedule(postId);
    }
    @ApiOperation("일정 삭제 기능")
    @DeleteMapping("user/schedule/{scheduleId}")
    ResponseEntity<String> deleteSchedule(@PathVariable Long scheduleId){
        return scheduleService.deleteSchedule(scheduleId);
    }
    @ApiOperation("모든 일정 삭제 기능")
    @DeleteMapping("user/plan/post/{postId}/schedules")
    ResponseEntity<String> deleteAllSchedule(@PathVariable Long postId){
        return scheduleService.deleteAllSchedule(postId);
    }
    @ApiOperation("검색한 지역 포함된 스케줄 불러오는 기능")
    @GetMapping("user/schedule")
    List<PostResponseDto> readSearchPost(@RequestParam String local){
        return scheduleService.readSearchPost(local);
    }
}
