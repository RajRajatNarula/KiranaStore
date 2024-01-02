package com.jar.KiranaRegister.Repositories;

import com.jar.KiranaRegister.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    // query to find product by using productId
    Product findByProductId(Long id);

}
