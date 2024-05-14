package com.micropos.products.mapper;

import java.util.Collection;

import com.micropos.products.model.Product;
import com.micropos.model.ProductDto;

public interface ProductMapper {

    Collection<ProductDto> toProductsDto(Collection<Product> products);
    Collection<Product> toProducts(Collection<ProductDto> productDtos);

    Product toProduct(ProductDto productDto);

    ProductDto toProductDto(Product product);
}
