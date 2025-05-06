package com.example.schedule_manager.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class Person {
    private String name;
    private String email;

    public Person(){}

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public abstract String getRole();

}
