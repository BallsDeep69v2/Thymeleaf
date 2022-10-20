package com.example.vmm.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="student_id")
    private Student student;

    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    @PastOrPresent
    @NotNull
    private LocalDate assigned_date;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Subject subject;


    @Range(min = 1, max = 5)
    @NotNull
    private Integer assigned_grade;

    public Grade(Student student, LocalDate assigned_date, Subject subject, int grade) {
        this.student = student;
        this.assigned_date = assigned_date;
        this.subject = subject;
        this.assigned_grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Grade grade = (Grade) o;
        return id != null && Objects.equals(id, grade.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
