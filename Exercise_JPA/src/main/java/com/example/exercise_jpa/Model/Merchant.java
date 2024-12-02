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
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Merchant name must not be empty")
    @Size(min = 4, message = "Merchant name must be at least 4 characters long")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String name;

    @NotNull
    @Column(columnDefinition = "boolean default true")
    private Boolean active;

    @Column(columnDefinition = "varchar(25)")
    private String city;

    @Column(columnDefinition = "varchar(25)")
    private String country;
}
