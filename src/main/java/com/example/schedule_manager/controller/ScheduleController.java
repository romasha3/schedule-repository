package com.example.schedule_manager.controller;

import com.example.schedule_manager.dto.ScheduleDTO;
import com.example.schedule_manager.model.*;
import com.example.schedule_manager.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ActivityService activityService;
    private final InstructorService instructorService;
    private final ClientService clientService;
    private final RoomService roomService;

    public ScheduleController(ScheduleService scheduleService,
                              ActivityService activityService,
                              InstructorService instructorService,
                              ClientService clientService,
                              RoomService roomService) {
        this.scheduleService = scheduleService;
        this.activityService = activityService;
        this.instructorService = instructorService;
        this.clientService = clientService;
        this.roomService = roomService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("schedules", scheduleService.getAll());
        return "schedule/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("scheduleDTO", new ScheduleDTO());
        addFormAttributes(model, null);
        return "schedule/form";
    }

    @GetMapping("/select-activity")
    public String filterInstructors(@ModelAttribute("scheduleDTO") ScheduleDTO dto, Model model) {
        model.addAttribute("scheduleDTO", dto);
        addFormAttributes(model, dto.getActivityId());
        return "schedule/form";
    }

    @PostMapping
    public String save(@ModelAttribute("scheduleDTO") @Valid ScheduleDTO dto,
                       BindingResult result,
                       Model model) {

        if (dto.getActivityId() == null || dto.getInstructorId() == null ||
                dto.getClientId() == null || dto.getRoomId() == null || dto.getStartTime() == null) {
            model.addAttribute("errorMessage", "⚠️ Усі поля повинні бути заповнені!");
            addFormAttributes(model, dto.getActivityId());
            return "schedule/form";
        }

        Schedule schedule = convertToEntity(dto);
        int count = scheduleService.countClientsInRoomWithOverlap(schedule.getRoom(), schedule);
        if (count >= schedule.getRoom().getCapacity()) {
            model.addAttribute("errorMessage", "⚠️ Немає місць у кімнаті в цей час.");
            addFormAttributes(model, dto.getActivityId());
            return "schedule/form";
        }

        scheduleService.save(schedule);
        return "redirect:/schedules";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Schedule schedule = scheduleService.getById(id);
        ScheduleDTO dto = convertToDTO(schedule);
        model.addAttribute("scheduleDTO", dto);
        addFormAttributes(model, dto.getActivityId());
        return "schedule/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return "redirect:/schedules";
    }

    private void addFormAttributes(Model model, Long activityId) {
        model.addAttribute("activities", activityService.getAll());
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("rooms", roomService.getAll());

        List<Instructor> instructors = instructorService.getAll();
        if (activityId != null) {
            Activity activity = activityService.getById(activityId);
            instructors = instructors.stream()
                    .filter(i -> i.getSpecialization() != null &&
                            i.getSpecialization().equalsIgnoreCase(activity.getTitle()))
                    .toList();
        }

        model.addAttribute("instructors", instructors);
    }

    private Schedule convertToEntity(ScheduleDTO dto) {
        Schedule schedule = new Schedule();
        schedule.setId(dto.getId());
        schedule.setStartTime(dto.getStartTime());
        schedule.setActivity(activityService.getById(dto.getActivityId()));
        schedule.setInstructor(instructorService.getById(dto.getInstructorId()));
        schedule.setClient(clientService.getById(dto.getClientId()));
        schedule.setRoom(roomService.getById(dto.getRoomId()));
        return schedule;
    }

    private ScheduleDTO convertToDTO(Schedule schedule) {
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
