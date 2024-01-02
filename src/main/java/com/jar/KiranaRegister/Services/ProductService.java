package com.jar.KiranaRegister.Services;

import com.jar.KiranaRegister.Dto.ProductDto;

import java.util.List;

public interface ProductService {

    // interface to insert new product
    ProductDto insertNewProduct(ProductDto productDto);

    // interface to retrive all products
    List<ProductDto> getAllProduct();
}
