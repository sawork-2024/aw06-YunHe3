package com.micropos.carts.mapper;

import com.micropos.model.ProductDto;
import com.micropos.carts.model.Product;

import java.util.Collection;

public interface ProductMapper {

    Collection<ProductDto> toProductsDto(Collection<Product> products);
    Collection<Product> toProducts(Collection<ProductDto> productDtos);

    Product toProduct(ProductDto productDto);

    ProductDto toProductDto(Product product);
}
