package com.example.schedule_manager.repository;

import com.example.schedule_manager.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
