package com.tranthien.watchstore.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.tranthien.watchstore.domain.Product;
import com.tranthien.watchstore.domain.dto.ProductCriteriaDTO;
import com.tranthien.watchstore.repository.ProductRepository;
import com.tranthien.watchstore.service.specification.ProductSpecs;

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

    //Test
    public Page<Product> fetchProductByName(Pageable pageable, String productName) {
        return this.productRepository.findAll(ProductSpecs.nameLike(productName), pageable);
    }

    //Test
    public Page<Product> fetchProductByMinPrice(Pageable pageable, double min) {
        return this.productRepository.findAll(ProductSpecs.minPrice(min), pageable);
    }

    //Test
    public Page<Product> fetchProductByMaxPrice(Pageable pageable, double max) {
        return this.productRepository.findAll(ProductSpecs.maxPrice(max), pageable);
    }

    //Test
    public Page<Product> fetchProductByPriceRange(Pageable pageable, String priceRange) {
        if (priceRange.equals("0m-to-1m")){
            double minPrice = 0;
            double maxPrice = 1000000;
            return this.productRepository.findAll(ProductSpecs.matchPriceRange(minPrice, maxPrice), pageable);
        } else if (priceRange.equals("1m-to-5m")) {
            double minPrice = 1000000;
            double maxPrice = 5000000;
            return this.productRepository.findAll(ProductSpecs.matchPriceRange(minPrice, maxPrice), pageable);
        } else if (priceRange.equals("5m-to-10m")) {
            double minPrice = 5000000;
            double maxPrice = 10000000;
            return this.productRepository.findAll(ProductSpecs.matchPriceRange(minPrice, maxPrice), pageable);
        } else if (priceRange.equals("10m-to-20m")) {
            double minPrice = 10000000;
            double maxPrice = 20000000;
            return this.productRepository.findAll(ProductSpecs.matchPriceRange(minPrice, maxPrice), pageable);
        } else {
            return this.productRepository.findAll(pageable);
        }
    }

    public Specification<Product> buildPriceSpecification(List<String> priceRange) {
        Specification<Product> combineSpec = Specification.where(null);
        for (String p : priceRange) {
            double min = 0;
            double max = 0;
            // Set the appropriate min and max based on the price range string
            switch (p) {
                case "lt-1m":
                    min = 1;
                    max = 1000000;
                    break;
                case "1m-3m":
                    min = 1000000;
                    max = 3000000;
                    break;
                case "3m-6m":
                    min = 3000000;
                    max = 6000000;
                    break;
                case "6m-9m":
                    min = 6000000;
                    max = 9000000;
                    break;
                case "9m-15m":
                    min = 9000000;
                    max = 15000000;
                    break;
                case "ge-15m":
                    min = 15000000;
                    max = 999999999;
                    break;
            }
            if (min != 0 && max != 0) {
                Specification<Product> rangeSpec = ProductSpecs.matchMultiPriceRange(min, max);
                combineSpec = combineSpec.or(rangeSpec);
            }
        }

        return combineSpec;

    }

    //Test
    public Page<Product> fetchProductByFactory(Pageable pageable, String factory) {
        return this.productRepository.findAll(ProductSpecs.factoryEqual(factory), pageable);
    }

    //Test
    public Page<Product> fetchProductByMultiFactory(Pageable pageable, List<String> factory) {
        return this.productRepository.findAll(ProductSpecs.matchMultiFactory(factory), pageable);
    }

    //Test
    public Page<Product> fetchProductByType(Pageable pageable, String type) {
        return this.productRepository.findAll(ProductSpecs.typeEqual(type), pageable);
    }

    //Test
    public Page<Product> fetchProductByMultiType(Pageable pageable, List<String> type) {
        return this.productRepository.findAll(ProductSpecs.matchMultiType(type), pageable);
    }

    public Page<Product> fetchProductWithSpecs(Pageable pageable, ProductCriteriaDTO productCriteriaDTO) {
        if (productCriteriaDTO.getType() == null && productCriteriaDTO.getFactory() == null && productCriteriaDTO.getPrice() == null) {
            return this.productRepository.findAll(pageable);
        }

        Specification<Product> combinedSpec = Specification.where(null);

        if (productCriteriaDTO.getType() != null && productCriteriaDTO.getType().isPresent()) {
            Specification<Product> currentSpec = ProductSpecs.matchMultiType(productCriteriaDTO.getType().get());
            combinedSpec = combinedSpec.and(currentSpec);
        }
        if (productCriteriaDTO.getFactory() != null && productCriteriaDTO.getFactory().isPresent()) {
            Specification<Product> currentSpec = ProductSpecs.matchMultiFactory(productCriteriaDTO.getFactory().get());
            combinedSpec = combinedSpec.and(currentSpec);
        }

        if (productCriteriaDTO.getPrice() != null && productCriteriaDTO.getPrice().isPresent()) {
            Specification<Product> currentSpecs = this.buildPriceSpecification(productCriteriaDTO.getPrice().get());
            combinedSpec = combinedSpec.and(currentSpecs);
        }

        return this.productRepository.findAll(combinedSpec, pageable);
    }
}
