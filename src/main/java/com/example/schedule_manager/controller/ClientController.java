package com.example.schedule_manager.controller;

import com.example.schedule_manager.model.Client;
import com.example.schedule_manager.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("clients", clientService.getAll());
        return "client/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("client", new Client());
        return "client/form";
    }

    @PostMapping
    public String save(@ModelAttribute Client client) {
        clientService.save(client);
        return "redirect:/clients";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("client", clientService.getById(id));
        return "client/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        clientService.delete(id);
        return "redirect:/clients";
    }
}