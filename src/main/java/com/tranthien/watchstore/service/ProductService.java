package com.tranthien.watchstore.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tranthien.watchstore.domain.Product;
import com.tranthien.watchstore.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> fetchProduct(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    public Product handleSaveProduct(Product product) {
        return this.productRepository.save(product);
    }

    public Product getProductById(long id) {
        return this.productRepository.findById(id).get();
    }

    public void handleDeleteProductById(long id) {
        this.productRepository.deleteById(id);
    }

    public long countProducts() {
        return this.productRepository.count();
    }

}
