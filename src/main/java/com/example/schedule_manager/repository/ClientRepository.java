package com.example.schedule_manager.repository;

import com.example.schedule_manager.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
