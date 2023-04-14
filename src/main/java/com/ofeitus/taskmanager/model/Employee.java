package com.ofeitus.taskmanager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "e_id", unique = true, nullable = false)
    private int id;

    @Column(name = "e_name")
    private String name;

    @OneToMany(mappedBy="employee")
    private List<Task> tasks;

    @Override
    public String toString() {
        return name;
    }
}
