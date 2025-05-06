package com.example.schedule_manager.controller;

import com.example.schedule_manager.model.Instructor;
import com.example.schedule_manager.service.InstructorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("instructors", instructorService.getAll());
        return "instructor/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "instructor/form";
    }

    @PostMapping
    public String save(@ModelAttribute Instructor instructor) {
        instructorService.save(instructor);
        return "redirect:/instructors";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("instructor", instructorService.getById(id));
        return "instructor/form";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        instructorService.delete(id);
        return "redirect:/instructors";
    }
}

