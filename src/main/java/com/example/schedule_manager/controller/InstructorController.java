package com.example.schedule_manager.controller;


import com.example.schedule_manager.model.Instructor;
import com.example.schedule_manager.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @GetMapping
    public String listInstructors(Model model) {
        model.addAttribute("instructors", instructorService.getAllInstructors());
        return "instructors";
    }

    @GetMapping("/new")
    public String showInstructorForm(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "instructor-form";
    }

    @PostMapping
    public String saveInstructor(@ModelAttribute Instructor instructor) {
        instructorService.saveInstructor(instructor);
        return "redirect:/instructors";
    }

    @GetMapping("/edit/{id}")
    public String editInstructor(@PathVariable Long id, Model model) {
        Optional<Instructor> instructor = instructorService.getInstructorById(id);
        model.addAttribute("instructor", instructor);
        return "instructor-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteInstructor(@PathVariable Long id) {
        instructorService.deleteInstructor(id);
        return "redirect:/instructors";
    }
}
