package com.example.schedule_manager.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Instructor extends Person implements Notifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String specialization;

    @Override
    public String getRole() {
        return "Instructor";
    }

    @Override
    public String notifyClient(String message) {
        return "Notify Instructor " + getName() + ": " + message;
    }

    @Override
    public String toString() {
        return getName();
    }
}
