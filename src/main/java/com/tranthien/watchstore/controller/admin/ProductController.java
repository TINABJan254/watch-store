package com.tranthien.watchstore.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tranthien.watchstore.domain.Product;
import com.tranthien.watchstore.service.ProductService;
import com.tranthien.watchstore.service.UploadFileService;

import jakarta.validation.Valid;

@Controller
public class ProductController {

    private final ProductService productService;
    private final UploadFileService uploadFileService;

    public ProductController(ProductService productService, UploadFileService uploadFileService){ 
        this.productService = productService;
        this.uploadFileService = uploadFileService;
    }
    
    @GetMapping("/admin/product")
    public String getProductPage(Model model) {
        List<Product> products = this.productService.fetchProduct();
        model.addAttribute("products", products);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/{id}")
    public String getProductDetailPage(Model model, @PathVariable long id){
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/product/detail";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model){
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String createProduct(Model model, @ModelAttribute("newProduct") @Valid Product product, BindingResult bindingResult, @RequestParam("thienFile") MultipartFile file){
        List<FieldError> errors = bindingResult.getFieldErrors();

        for (FieldError error : errors){
            System.out.println(">>>>>>" + error.getField() + " - " + error.getDefaultMessage());
        }

        if (bindingResult.hasErrors()){
            return "admin/product/create";
        }

        String image = this.uploadFileService.handleSaveUploadedFile(file, "product");

        product.setImage(image);
        this.productService.handleSaveProduct(product);

        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProductPage(Model model, @PathVariable long id){
        Product product = this.productService.getProductById(id);
        model.addAttribute("newProduct", product);
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String updateProduct(Model model, @ModelAttribute("newProduct") Product updatedProduct, @RequestParam("thienFile") MultipartFile file){
        
        Product currentProduct = this.productService.getProductById(updatedProduct.getId());
        if (currentProduct !=  null){
            currentProduct.setName(updatedProduct.getName());
            currentProduct.setQuantity(updatedProduct.getQuantity());
            currentProduct.setPrice(updatedProduct.getPrice());
            currentProduct.setType(updatedProduct.getType());
            currentProduct.setFactory(updatedProduct.getFactory());
            currentProduct.setShortDesc(updatedProduct.getShortDesc());
            currentProduct.setDetailDesc(updatedProduct.getDetailDesc());

            if (!file.isEmpty()){
                String image = this.uploadFileService.handleSaveUploadedFile(file, "product");
                currentProduct.setImage(image);
            }

            this.productService.handleSaveProduct(currentProduct);
        }

        return "redirect:/admin/product";
    }

    @PostMapping("/admin/product/delete")
    public String deleteProduct(@RequestParam("id") Long id){
        this.productService.handleDeleteProductById(id);
        return "redirect:/admin/product";
    }

}
