package com.example.schedule_manager.repository;

import com.example.schedule_manager.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByRoomIdAndStartTime(Long roomId, LocalDateTime startTime);

    List<Schedule> findByRoomId(Long roomId);

    List<Schedule> findByActivityIdAndRoomIdAndStartTime(Long activityId, Long roomId, LocalDateTime startTime);
}
