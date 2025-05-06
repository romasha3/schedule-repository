package com.example.schedule_manager.model;

import jakarta.persistence.*;
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

    @ManyToOne
    private Activity activity;

    @ManyToOne
    private Instructor instructor;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Room room;

    private LocalDateTime startTime;

    @Override
    public boolean isSchedulable() {
        return room != null && startTime != null;
    }
}
