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
public class MerchantStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Product ID cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer productID;

    @NotNull(message = "Merchant ID cannot be null")
    @Column(columnDefinition = "int not null")
    private Integer merchantID;

    @NotNull(message = "Stock cannot be null")
    @Min(value = 10, message = "Stock must be at least 10")
    @Column(columnDefinition = "int not null")
    private Integer stock;
}
