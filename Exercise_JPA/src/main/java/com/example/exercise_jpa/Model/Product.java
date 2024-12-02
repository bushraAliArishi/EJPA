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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Product name must not be empty")
    @Size(min = 3, message = "Product name must be at least 3 characters long")
    @Column(columnDefinition = "varchar(25) not null")
    private String name;

    @NotNull(message = "Price must not be null")
    @Positive(message = "Price must be positive")
    @Column(columnDefinition = "double not null")
    private Double price;

    @NotNull(message = "Category ID cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer categoryID;

    @Column(columnDefinition = "boolean default false")
    private Boolean flashSale;

    @Column(columnDefinition = "double default 0.0")
    private Double flashSalePrice;

    @Column(columnDefinition = "int default 0")
    private Integer flashSaleDuration;

    @Column(columnDefinition = "double")
    private Double originalPrice;

    @Column(columnDefinition = "int default 0")
    private Integer buyCount;

    @Column(columnDefinition = "date")
    private String expiryDate;
}
