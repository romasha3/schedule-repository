package com.example.schedule_manager.controller;

import com.example.schedule_manager.model.Room;
import com.example.schedule_manager.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("rooms", roomService.getAll());
        return "room/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("room", new Room());
        return "room/form";
    }

    @PostMapping
    public String save(@ModelAttribute Room room, Model model) {
        try {
            roomService.save(room);
            return "redirect:/rooms";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("room", room);
            return "room/form";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("room", roomService.getById(id));
        return "room/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        roomService.delete(id);
        return "redirect:/rooms";
    }
}