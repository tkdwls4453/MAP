package com.sparta.finalprojectback.schedule.service;

import com.sparta.finalprojectback.post.dto.PostResponseDto;
import com.sparta.finalprojectback.post.model.Post;
import com.sparta.finalprojectback.post.repository.PostRepository;
import com.sparta.finalprojectback.schedule.controller.ScheduleController;
import com.sparta.finalprojectback.schedule.dto.ScheduleRequestDto;
import com.sparta.finalprojectback.schedule.dto.ScheduleResponseDto;
import com.sparta.finalprojectback.schedule.model.Schedule;
import com.sparta.finalprojectback.schedule.repository.ScheduleRepository;
import com.sparta.finalprojectback.statuscode.ResponseMessage;
import com.sparta.finalprojectback.statuscode.StatusCode;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final Logger logger = LoggerFactory.getLogger(ScheduleController.class);
    private final ScheduleRepository scheduleRepository;
    private final PostRepository postRepository;
    @Override
    public ResponseEntity<Long> createSchedule(ScheduleRequestDto requestDto) {
        Post post = postRepository.findById(requestDto.getPostId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 게시물 아이디 입니다.")
        );
        Long scheduleId =  scheduleRepository.save(Schedule.builder()
                .post(post)
                .placeName(requestDto.getPlaceName())
                .address(requestDto.getAddress())
                .link(requestDto.getLink())
                .date(requestDto.getDate())
                .x(requestDto.getX())
                .y(requestDto.getY())
                .phone(requestDto.getPhone())
                .build()).getId();

        return new ResponseEntity<>(scheduleId, HttpStatus.valueOf(StatusCode.CREATED));
    }

    @Override
    public ResponseEntity<List<ScheduleResponseDto>> readSchedule(Long postId) {

        List<Schedule> scheduleList = scheduleRepository.findAllByPost_Id(postId);
        List<ScheduleResponseDto> resultList = new ArrayList<>();

        for (Schedule schedule : scheduleList) {
            resultList.add(ScheduleResponseDto.builder()
                    .id(schedule.getId())
                    .placeName(schedule.getPlaceName())
                    .link(schedule.getLink())
                    .date(schedule.getDate())
                    .address(schedule.getAddress())
                    .x(schedule.getX())
                    .y(schedule.getY())
                    .phone(schedule.getPhone())
                    .postId(schedule.getPost().getId())
                    .build());
        }
        return new ResponseEntity<>(resultList, HttpStatus.valueOf(StatusCode.OK));
    }

    @Override
    public ResponseEntity<String> deleteSchedule(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
        logger.info("deleteScheduleId : {}", scheduleId);
        return new ResponseEntity<>(ResponseMessage.DELETE_SCHEDULE, HttpStatus.valueOf(StatusCode.OK));
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteAllSchedule(Long postId) {
        scheduleRepository.deleteAllByPost_Id(postId);
        logger.info("deleteAllScheduleId : {}", postId);
        return new ResponseEntity<>(ResponseMessage.DELETE_ALL_SCHEDULE, HttpStatus.valueOf(StatusCode.OK));
    }

    public List<PostResponseDto> readSearchPost(String local){
        String[] localName = local.split(",| ");
        if (Objects.equals(localName[0], "")) {
            throw new NullPointerException("입력 값이 없습니다.");
        }
        System.out.println(local);
        List<Long> postIdList = new ArrayList<>();
        for (int i = 0; i < localName.length; i++){
            List<Schedule> scheduleList = scheduleRepository.findByAddressContaining(localName[i]);
            for (Schedule schedule : scheduleList){
                postIdList.add(schedule.getPost().getId());
            }
        }
        List<PostResponseDto> responseDtoList = new ArrayList<>();
        List<Long> resultList = postIdList.stream().distinct().collect(Collectors.toList());
        for (Long postId : resultList){
            Post post = postRepository.findById(postId).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 게시물 아이디 입니다.")
            );
            responseDtoList.add(PostResponseDto.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .category(post.getCategory())
                    .nickname(post.getMember().getNickname())
                    .createdAt(post.getCreatedAt())
                    .modifiedAt((post.getModifiedAt()))
                    .period(post.getPeriod())
                    .image(post.getImage())
                    .likes(post.getLikes())
                    .build());
        }
        logger.info("readSearchPost : {}",local);
        return responseDtoList;
    }
}
