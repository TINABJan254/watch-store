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

        /*Console log*/
        System.out.println("----------------------View product detail-----------------------");
        System.out.println(">>>>> Product name: " + product.getName());
        System.out.println(">>>>> Quantity: " + product.getQuantity());
        System.out.println(">>>>> Price: " + product.getPrice());
        System.out.println(">>>>> Type: " + product.getType());
        System.out.println(">>>>> Factory: " + product.getFactory());
        System.out.println(">>>>> Short Desc: " + product.getShortDesc());
        System.out.println(">>>>> Detail Desc: " + product.getDetailDesc());
        System.out.println(">>>>> Image: " + product.getImage()); 
        System.out.println("---------------------------------------------------------------");
        /*End console log*/

        return "client/item/detail";
    }
}
