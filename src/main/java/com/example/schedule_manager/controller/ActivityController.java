package com.example.schedule_manager.controller;


import com.example.schedule_manager.model.Activity;
import com.example.schedule_manager.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping
    public String listActivities(Model model) {
        model.addAttribute("activities", activityService.getAllActivities());
        return "activities";
    }

    @GetMapping("/new")
    public String showActivityForm(Model model) {
        model.addAttribute("activity", new Activity());
        return "activity-form";
    }

    @PostMapping
    public String saveActivity(@ModelAttribute Activity activity) {
        activityService.saveActivity(activity);
        return "redirect:/activities";
    }

    @GetMapping("/edit/{id}")
    public String editActivity(@PathVariable Long id, Model model) {
        Optional<Activity> activity = activityService.getActivityById(id);
        model.addAttribute("activity", activity);
        return "activity-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteActivity(@PathVariable Long id) {
        activityService.deleteActivity(id);
        return "redirect:/activities";
    }
}
