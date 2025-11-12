package com.example.jpa_auditing.service;

import com.example.jpa_auditing.domain.Product;
import com.example.jpa_auditing.domain.ProductDTO;
import com.example.jpa_auditing.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product create(ProductDTO newProductDto) {
        Product product = new Product(null,newProductDto.name(), newProductDto.price(), newProductDto.description());
        return repository.save(product);
    }

    public Product update(ProductDTO updateData, Long id) {
        Product product = repository.findById(id).orElseThrow();
        product.setName(updateData.name());
        product.setPrice(updateData.price());
        product.setDescription(updateData.description());
        return repository.save(product);
    }
}
