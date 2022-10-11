package com.example.person_interest.domain;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Table(name="persons")
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Pattern(regexp = "[A-ZÄÖÜ][a-zäöüß]*", message = "Vorname entspricht nicht dem vorgegebenen Muster")
    @Size(max = 30)
    private String firstName;

    @Pattern(regexp = "[A-ZÄÖÜ][a-zäöüß]*", message = "Nachname entspricht nicht dem vorgegebenen Muster")
    @Size(max = 30)
    private String lastName;

    @Past(message = "Localdate muss in der Vergangenheit liegen")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Geburtsdatum muss ausgefüllt werden")
    private LocalDate dateOfBirth;

    @ManyToMany
    @Size(max = 3, message = "Es dürfen maximal 3 Interests ausgewählt werden")
    @JoinTable(name="persons_interests")
    private Set<Interest> interests = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Person person = (Person) o;
        return id != null && Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "dateOfBirth = " + dateOfBirth + ", " +
                "sex = " + sex + ")";
    }
}
