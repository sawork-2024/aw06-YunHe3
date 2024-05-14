package com.micropos.products.mapper;


import com.micropos.model.ProductDto;
import com.micropos.products.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
@Component
public class ProductMapperImp implements ProductMapper{
    @Override
    public Collection<ProductDto> toProductsDto(Collection<Product> products) {
        ArrayList<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(toProductDto(product));
        }
        return productDtos;
    }

    @Override
    public Collection<Product> toProducts(Collection<ProductDto> productDtos) {
        ArrayList<Product> products = new ArrayList<>();
        for (ProductDto productDto : productDtos) {
            products.add(toProduct(productDto));
        }
        return products;
    }

    @Override
    public Product toProduct(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setImg(product.getImg());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        return product;
    }

    @Override
    public ProductDto toProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setImg(product.getImg());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        return productDto;
    }
}
