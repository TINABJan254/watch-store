package com.tranthien.watchstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tranthien.watchstore.domain.Product;
import com.tranthien.watchstore.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> fetchProduct() {
        return this.productRepository.findAll();
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

}
