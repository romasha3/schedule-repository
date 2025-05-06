package com.example.schedule_manager.controller;

import com.example.schedule_manager.model.Room;
import com.example.schedule_manager.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public String listRooms(Model model) {
        model.addAttribute("rooms", roomService.getAllRooms());
        return "rooms";
    }

    @GetMapping("/new")
    public String showRoomForm(Model model) {
        model.addAttribute("room", new Room());
        return "room-form";
    }

    @PostMapping
    public String saveRoom(@ModelAttribute Room room) {
        roomService.saveRoom(room);
        return "redirect:/rooms";
    }

    @GetMapping("/edit/{id}")
    public String editRoom(@PathVariable Long id, Model model) {
        Optional<Room> room = roomService.getRoomById(id);
        model.addAttribute("room", room);
        return "room-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return "redirect:/rooms";
    }
}