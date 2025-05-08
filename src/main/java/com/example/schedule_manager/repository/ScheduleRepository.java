package com.example.schedule_manager.repository;

import com.example.schedule_manager.model.Room;
import com.example.schedule_manager.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByRoomAndStartTime(Room room, LocalDateTime startTime);
}
