package com.jar.KiranaRegister.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {

    private String productName;  // Name of the product
    private int productCount; // Count of the product
    private double productAmount; // Cost of product
}
