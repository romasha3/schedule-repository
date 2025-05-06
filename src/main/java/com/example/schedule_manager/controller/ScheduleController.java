package com.example.schedule_manager.controller;

import com.example.schedule_manager.model.Schedule;
import com.example.schedule_manager.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private RoomService roomService;

    @GetMapping
    public String listSchedules(Model model) {
        model.addAttribute("schedules", scheduleService.getAllSchedules());
        return "schedule";
    }

    @GetMapping("/new")
    public String showScheduleForm(Model model) {
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("activities", activityService.getAllActivities());
        model.addAttribute("instructors", instructorService.getAllInstructors());
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("rooms", roomService.getAllRooms());
        return "schedule-form";
    }

    @PostMapping
    public String saveSchedule(@ModelAttribute Schedule schedule) {
        scheduleService.saveSchedule(schedule);
        return "redirect:/schedule";
    }

    @GetMapping("/edit/{id}")
    public String editSchedule(@PathVariable Long id, Model model) {
        Optional<Schedule> schedule = scheduleService.getScheduleById(id);
        model.addAttribute("schedule", schedule);
        model.addAttribute("activities", activityService.getAllActivities());
        model.addAttribute("instructors", instructorService.getAllInstructors());
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("rooms", roomService.getAllRooms());
        return "schedule-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return "redirect:/schedule";
    }
}