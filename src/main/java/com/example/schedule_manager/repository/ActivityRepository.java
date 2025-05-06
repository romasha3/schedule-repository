package com.example.schedule_manager.repository;

import com.example.schedule_manager.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
