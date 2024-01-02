package com.jar.KiranaRegister.Controllers;

import com.jar.KiranaRegister.Dto.ProductDto;
import com.jar.KiranaRegister.Services.impl.ProductServiceImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController
{
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductServiceImpl productService;
    // Controller to create new Product record
    @PostMapping
    public ResponseEntity<ProductDto> insertProduct(@RequestBody ProductDto productDto)
    {
        logger.info("Entered insertProduct in ProductController");
        return  new ResponseEntity<>(productService.insertNewProduct(productDto), HttpStatus.CREATED);
    }

    // Controller to retrieve list of all Product records
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        logger.info("Entered getAllProucts in ProductController");
        return new ResponseEntity<>(productService.getAllProduct(),HttpStatus.OK);
    }

}
