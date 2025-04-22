package com.tranthien.watchstore.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tranthien.watchstore.domain.Product;
import com.tranthien.watchstore.service.ProductService;

@Controller
public class ItemController {
    
    private final ProductService productService;

    public ItemController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String getItemDetailPage(@PathVariable long id, Model model){
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        return "client/item/detail";
    }
}
