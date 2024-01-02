package com.jar.KiranaRegister.Services.impl;

import com.jar.KiranaRegister.Dto.ProductDto;
import com.jar.KiranaRegister.Entities.Product;
import com.jar.KiranaRegister.Mapper.ProductMapper;
import com.jar.KiranaRegister.Repositories.ProductRepository;
import com.jar.KiranaRegister.Services.ProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private ProductRepository productRepository;
    private final ProductMapper productMapper;
    // Service method to insert new product record
    public ProductDto insertNewProduct(ProductDto productDto) {
        logger.info("Entered insertNewProduct in ProductServiceImpl");
        Product product=productMapper.mapProductDtoToProduct(productDto);
        productRepository.save(product);
        return productDto;
    }

    // Service method to retrieve all product record
    public List<ProductDto> getAllProduct() {
        logger.info("Entered getAllProduct in ProductServiceImpl");
        List<ProductDto> productDtos= productRepository.findAll().stream().map(productMapper :: mapProductToProductDto).collect(Collectors.toList());
        if(productDtos.isEmpty())
        {
            logger.info("No results found");
        }
        return productDtos;
    }
}
