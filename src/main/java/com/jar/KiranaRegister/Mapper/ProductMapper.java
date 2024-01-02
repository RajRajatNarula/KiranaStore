package com.jar.KiranaRegister.Mapper;

import com.jar.KiranaRegister.Dto.ProductDto;
import com.jar.KiranaRegister.Entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto mapProductToProductDto(Product product);

    Product mapProductDtoToProduct(ProductDto productDto);
}
