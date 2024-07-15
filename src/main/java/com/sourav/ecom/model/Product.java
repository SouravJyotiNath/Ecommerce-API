package com.sourav.ecom.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {

    @Id
    private int id;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean available;
    private int quantity;
    private String brand;
    private Date date;
    private String category;
}
