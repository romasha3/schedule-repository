
package com.example.schedule_manager.controller;

import com.example.schedule_manager.model.Schedule;
import com.example.schedule_manager.service.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("schedule", new Schedule());
        addFormAttributes(model);
        return "schedule/form";
    }

    @PostMapping
    public String save(@ModelAttribute @Valid Schedule schedule,
                       BindingResult result,
                       Model model) {

        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "⚠️ Усі поля повинні бути заповнені!");
            addFormAttributes(model);
            return "schedule/form";
        }

        int count = scheduleService.countClientsInRoomWithOverlap(schedule.getRoom(), schedule);
        if (count >= schedule.getRoom().getCapacity()) {
            model.addAttribute("errorMessage", "⚠️ Немає місць у кімнаті в цей час.");
            addFormAttributes(model);
            return "schedule/form";
        }

        scheduleService.save(schedule);
        return "redirect:/schedules";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("schedule", scheduleService.getById(id));
        addFormAttributes(model);
        return "schedule/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        scheduleService.delete(id);
        return "redirect:/schedules";
    }

    private void addFormAttributes(Model model) {
        model.addAttribute("activities", activityService.getAll());
        model.addAttribute("instructors", instructorService.getAll());
        model.addAttribute("clients", clientService.getAll());
        model.addAttribute("rooms", roomService.getAll());
    }
}
