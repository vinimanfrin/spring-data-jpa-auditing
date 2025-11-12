package com.example.jpa_auditing.controller;

import com.example.jpa_auditing.domain.Product;
import com.example.jpa_auditing.domain.ProductDTO;
import com.example.jpa_auditing.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO newProductDto){
        return ResponseEntity.ok(service.create(newProductDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@RequestBody ProductDTO updateData, @PathVariable Long id){
        return ResponseEntity.ok(service.update(updateData,id));
    }
}
