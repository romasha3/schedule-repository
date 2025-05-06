package com.example.schedule_manager.controller;

import com.example.schedule_manager.model.Client;
import com.example.schedule_manager.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String listClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "clients";
    }

    @GetMapping("/new")
    public String showClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "client-form";
    }

    @PostMapping
    public String saveClient(@ModelAttribute Client client) {
        clientService.saveClient(client);
        return "redirect:/clients";
    }

    @GetMapping("/edit/{id}")
    public String editClient(@PathVariable Long id, Model model) {
        Optional<Client> client = clientService.getClientById(id);
        model.addAttribute("client", client);
        return "client-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return "redirect:/clients";
    }
}
