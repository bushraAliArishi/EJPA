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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Username must not be empty")
    @Size(min = 5, message = "Username must be at least 5 characters long")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String userName;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 5, message = "Password must be at least 5 characters long")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$", message = "Password must contain at least one letter and one digit")
    @Column(columnDefinition = "varchar(15) not null")
    private String password;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email must be valid")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String email;

    @NotNull(message = "Role cannot be null")
    @Pattern(regexp = "^(Admin|Customer)$", message = "Role must be 'Admin' or 'Customer'")
    @Column(columnDefinition = "varchar(10) not null")
    private String role;

    @NotNull(message = "Balance cannot be null")
    @PositiveOrZero(message = "Balance must be zero or positive")
    @Column(columnDefinition = "double not null")
    private Double balance;
}
