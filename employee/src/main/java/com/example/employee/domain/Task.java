package com.example.employee.domain;


import lombok.Getter;
import lombok.NonNull;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String description;

    @Min(0)
    private int hoursWorked;

    @Past
    @Column(name="finished_date")
    private LocalDate finished;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee assignee;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Task task = (Task) o;
        return id != null && Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
