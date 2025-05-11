package com.example.schedule_manager.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client extends Person implements Notifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;

    @Override
    public String getRole() {
        return "Client";
    }

    @Override
    public String notifyClient(String message) {
        return "Notify Client " + getName() + ": " + message;
    }

    @Override
    public String toString() {
        return getName();
    }
}
