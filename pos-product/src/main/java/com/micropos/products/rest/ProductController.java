package com.micropos.products.rest;

import com.micropos.api.ProductsApi;
import com.micropos.model.ProductDto;
import com.micropos.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.micropos.products.mapper.ProductMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController implements ProductsApi {
    private ProductService productService;
    private ProductMapper productMapper;

    @Autowired
    public void setProductMapper(ProductMapper productMapper) { this.productMapper = productMapper; }
    @Autowired
    public void setProductService(ProductService productService) { this.productService = productService; }
    @Override
    public ResponseEntity<List<ProductDto>> listProducts() {
        return ResponseEntity.ok(new ArrayList<>(productMapper.toProductsDto(productService.products())));
    }

    @Override
    @LoadBalanced
    public ResponseEntity<ProductDto> getProductById(String productId) {
        return ResponseEntity.ok(productMapper.toProductDto(productService.product(productId)));
    }
}
