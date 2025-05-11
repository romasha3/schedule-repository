package com.example.schedule_manager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Schedule implements Schedulable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Заняття обов'язкове")
    @ManyToOne
    private Activity activity;

    @NotNull(message = "Інструктор обов'язковий")
    @ManyToOne
    private Instructor instructor;

    @NotNull(message = "Клієнт обов'язковий")
    @ManyToOne
    private Client client;

    @NotNull(message = "Кімната обов'язкова")
    @ManyToOne
    private Room room;

    @NotNull(message = "Дата та час обов'язкові")
    private LocalDateTime startTime;

    @Override
    public boolean isSchedulable() {
        return room != null && startTime != null;
    }
}
