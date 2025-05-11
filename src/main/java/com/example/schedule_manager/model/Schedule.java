package com.example.schedule_manager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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

    @NotNull(message = "Потрібно вибрати заняття")
    @ManyToOne
    private Activity activity;

    @NotNull(message = "Потрібно вибрати інструктора")
    @ManyToOne
    private Instructor instructor;

    @NotNull(message = "Потрібно вибрати клієнта")
    @ManyToOne
    private Client client;

    @NotNull(message = "Потрібно вибрати кімнату")
    @ManyToOne
    private Room room;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "Потрібно вказати дату та час")
    private LocalDateTime startTime;


    @Override
    public boolean isSchedulable() {
        return room != null && startTime != null;
    }
}
