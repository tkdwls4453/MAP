package com.sparta.finalprojectback.schedule.repository;

import com.sparta.finalprojectback.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByPost_Id(Long postId);
    void deleteAllByPost_Id(Long postId);
    List<Schedule> findByAddressContaining(String local);
}