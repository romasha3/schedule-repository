package com.example.schedule_manager.service;

import com.example.schedule_manager.model.Room;
import com.example.schedule_manager.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    public Room getById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public void save(Room room) {
        boolean exists = roomRepository.findAll().stream()
                .anyMatch(r -> r.getRoomNumber().equalsIgnoreCase(room.getRoomNumber()) &&
                        r.getCapacity() == room.getCapacity() &&
                        (room.getId() == null || !r.getId().equals(room.getId())));
        if (exists) {
            throw new IllegalArgumentException("Кімната з таким номером та місткістю вже існує.");
        }
        roomRepository.save(room);
    }

    public void delete(Long id) {
        roomRepository.deleteById(id);
    }
}
