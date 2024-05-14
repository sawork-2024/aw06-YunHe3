package com.micropos.products.service;

import com.micropos.products.db.RedisService;
import com.micropos.products.model.Product;
import com.micropos.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceImp implements ProductService{
    private ProductRepository productRepository;
    private RedisService redisService;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) { this.productRepository = productRepository; }
    @Autowired
    public void setRedisService(RedisService redisService) { this.redisService = redisService; }

    @Override
    public List<Product> products() {

        if (redisService.isEmpty()) {
            // redis中无需要的资源，读取jD缓存至redis
            List<Product> products = productRepository.getProducts();
            for (Product p : products) {
                redisService.setValue(p.getId().toString(), p);
            }
        }

        return redisService.getValues();
    }

    @Override
    public Product product(String pid) {

        Product product = redisService.getValue(pid);
        if (product == null) {
            product = productRepository.getProduct(pid);
            if (product == null) return null;
            redisService.setValue(product.getId().toString(), product);
        }

        return product;
    }
}
