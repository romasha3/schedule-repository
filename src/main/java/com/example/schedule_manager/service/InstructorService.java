package com.example.schedule_manager.service;

import com.example.schedule_manager.model.Instructor;
import com.example.schedule_manager.repository.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<Instructor> getAll() {
        return instructorRepository.findAll();
    }

    public Instructor getById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
    }

    public void save(Instructor instructor) {
        instructorRepository.save(instructor);
    }

    public void delete(Long id) {
        instructorRepository.deleteById(id);
    }
}

