package com.jar.KiranaRegister.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId; // productId will be auto generated
    private String productName;  // Name of the product
    private int productCount; // Count of the product
    private double productAmount; // Cost of product

}
