package com.example.exercise_jpa.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Category name must not be empty")
    @Size(min = 4, message = "Category name must be at least 4 characters long")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String name;
}
