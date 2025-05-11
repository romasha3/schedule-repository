package com.example.schedule_manager.controller;

import com.example.schedule_manager.model.Schedule;
import com.example.schedule_manager.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final ActivityService activityService;
    private final InstructorService instructorService;
    private final ClientService clientService;
    private final RoomService roomService;

    public ScheduleController(ScheduleService scheduleService, ActivityService activityService,
                              InstructorService instructorService, ClientService clientService,
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
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("activities", activityService.getAll());
        model.addAttribute("instructors", instructorService.getAll());
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("rooms", roomService.getAll());
        return "schedule/form";
    }

    @PostMapping
    public String save(@ModelAttribute Schedule schedule, Model model) {
        if (schedule.getStartTime() == null ||
                schedule.getActivity() == null ||
                schedule.getRoom() == null ||
                schedule.getInstructor() == null ||
                schedule.getClient() == null) {

            model.addAttribute("schedule", schedule);
            model.addAttribute("activities", activityService.getAll());
            model.addAttribute("instructors", instructorService.getAll());
            model.addAttribute("clients", clientService.getAll());
            model.addAttribute("rooms", roomService.getAll());
            model.addAttribute("errorMessage", " Усі поля обов’язкові. Перевірте, чи ви все заповнили.");
            return "schedule/form";
        }

        int count = scheduleService.countClientsInRoom(schedule.getRoom(), schedule.getStartTime());
        int capacity = schedule.getRoom().getCapacity();

        if (count >= capacity) {
            model.addAttribute("schedule", schedule);
            model.addAttribute("activities", activityService.getAll());
            model.addAttribute("instructors", instructorService.getAll());
            model.addAttribute("clients", clientService.getAll());
            model.addAttribute("rooms", roomService.getAll());
            model.addAttribute("errorMessage", " У кімнаті вже немає вільних місць на цей час.");
            return "schedule/form";
        }

        scheduleService.save(schedule);
        return "redirect:/schedules";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("schedule", scheduleService.getById(id));
        model.addAttribute("activities", activityService.getAll());
        model.addAttribute("instructors", instructorService.getAll());
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("rooms", roomService.getAll());
        return "schedule/form";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return "redirect:/schedules";
    }
}
