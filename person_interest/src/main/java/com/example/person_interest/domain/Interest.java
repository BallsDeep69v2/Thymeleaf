package com.example.person_interest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Table(name="interests")
@Getter
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @UniqueElements
    private String description;

    public Interest(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
