package com.ofeitus.taskmanager.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "t_id", unique = true, nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name="t_employee", nullable=false)
    private Employee employee;

    @Column(name = "t_name")
    private String name;

    @Column(name = "t_start_date")
    private Date startDate;

    @Column(name = "t_end_date")
    private Date endDate;

    @Column(name = "t_completion_date")
    private Date completionDate;

    @Column(name = "t_is_completed")
    private boolean isCompleted;
}
