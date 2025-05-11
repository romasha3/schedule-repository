package com.example.schedule_manager.service;

import com.example.schedule_manager.dto.ScheduleDTO;
import com.example.schedule_manager.model.*;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {

    private final ActivityService activityService;
    private final InstructorService instructorService;
    private final ClientService clientService;
    private final RoomService roomService;

    public ScheduleMapper(ActivityService activityService,
                          InstructorService instructorService,
                          ClientService clientService,
                          RoomService roomService) {
        this.activityService = activityService;
        this.instructorService = instructorService;
        this.clientService = clientService;
        this.roomService = roomService;
    }

    public Schedule toEntity(ScheduleDTO dto) {
        Schedule schedule = new Schedule();
        schedule.setId(dto.getId());
        schedule.setStartTime(dto.getStartTime());
        schedule.setActivity(activityService.getById(dto.getActivityId()));
        schedule.setInstructor(instructorService.getById(dto.getInstructorId()));
        schedule.setClient(clientService.getById(dto.getClientId()));
        schedule.setRoom(roomService.getById(dto.getRoomId()));
        return schedule;
    }

    public ScheduleDTO toDTO(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(schedule.getId());
        dto.setStartTime(schedule.getStartTime());
        dto.setActivityId(schedule.getActivity().getId());
        dto.setInstructorId(schedule.getInstructor().getId());
        dto.setClientId(schedule.getClient().getId());
        dto.setRoomId(schedule.getRoom().getId());
        return dto;
    }
}
