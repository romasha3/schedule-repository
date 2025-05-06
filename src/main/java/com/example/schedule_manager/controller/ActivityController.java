package com.example.schedule_manager.controller;


import com.example.schedule_manager.model.Activity;
import com.example.schedule_manager.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("activities", activityService.getAll());
        return "activity/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("activity", new Activity());
        return "activity/form";
    }

    @PostMapping
    public String save(@ModelAttribute Activity activity) {
        activityService.save(activity);
        return "redirect:/activities";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("activity", activityService.getById(id));
        return "activity/form";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        activityService.delete(id);
        return "redirect:/activities";
    }
}


