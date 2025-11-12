package com.example.jpa_auditing.service;

import com.example.jpa_auditing.domain.Product;
import com.example.jpa_auditing.domain.ProductDTO;
import com.example.jpa_auditing.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private UserService userService;

    public Product create(ProductDTO newProductDto) {
        String createdBy = userService.findRandomUser().getName();
        LocalDateTime createdAt = LocalDateTime.now();
        Product product = new Product(null,newProductDto.name(), newProductDto.price(), newProductDto.description(), createdBy,null, createdAt,null);
        return repository.save(product);
    }

    public Product update(ProductDTO updateData, Long id) {
        Product product = repository.findById(id).orElseThrow();
        String updatedBy = userService.findRandomUser().getName();
        product.setName(updateData.name());
        product.setPrice(updateData.price());
        product.setDescription(updateData.description());
        product.setUpdatedBy(updatedBy);
        product.setUpdatedAt(LocalDateTime.now());
        return repository.save(product);
    }
}
