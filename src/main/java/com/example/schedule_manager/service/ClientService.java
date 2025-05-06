package com.example.schedule_manager.service;

import com.example.schedule_manager.model.Client;
import com.example.schedule_manager.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    public Client getById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client not found"));
    }

    public void save(Client client) {
        clientRepository.save(client);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
