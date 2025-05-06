package com.example.schedule_manager.repository;

import com.example.schedule_manager.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
