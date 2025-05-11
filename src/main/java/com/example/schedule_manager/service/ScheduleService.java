package com.example.schedule_manager.service;

import com.example.schedule_manager.model.Room;
import com.example.schedule_manager.model.Schedule;
import com.example.schedule_manager.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    public Schedule getById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
    }

    public void save(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }

    public int countClientsInRoomWithOverlap(Room room, Schedule newSchedule) {
        LocalDateTime start2 = newSchedule.getStartTime();
        LocalDateTime end2 = start2.plusMinutes(newSchedule.getActivity().getDurationInMinutes());

        return (int) scheduleRepository.findAll().stream()
                .filter(s -> s.getRoom().equals(room))
                .filter(s -> {
                    LocalDateTime start1 = s.getStartTime();
                    LocalDateTime end1 = start1.plusMinutes(s.getActivity().getDurationInMinutes());
                    return start1.isBefore(end2) && start2.isBefore(end1);
                })
                .count();
    }
}
