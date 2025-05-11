package com.example.schedule_manager.repository;

import com.example.schedule_manager.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
