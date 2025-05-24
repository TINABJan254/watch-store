package com.tranthien.watchstore.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.tranthien.watchstore.domain.dto.ProductDTO;
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
    public String getProductPage(Model model, 
        @RequestParam("page") Optional<String> pageOptional,
        @RequestParam("limit") Optional<String> limitOptional) {
        
        int page = 1;
        int limit = 10;
        try {
            if (pageOptional.isPresent()) {
                // Convert from String to int
                page = Integer.parseInt(pageOptional.get());
            }

            if (limitOptional.isPresent()) {
                limit = Integer.parseInt(limitOptional.get());
                if (limit > 20) {
                    limit = 20;
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        
        Pageable pageable = PageRequest.of(page - 1, limit);
        // Page<Product> productPage = this.productService.fetchProduct(pageable);
        // List<Product> products = productPage.getContent();

        Page<ProductDTO> productPage2 = this.productService.fetchProduct2(pageable);
        List<ProductDTO> productDTOs = productPage2.getContent();

        model.addAttribute("products", productDTOs);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage2.getTotalPages());
        model.addAttribute("limit", limit);

        /*Console log*/
        System.out.println("---------------------------------------------------------------");
        System.out.println(">>>>> Limit: " + limit + ", page: " + page);
        for (ProductDTO p : productDTOs) {
            System.out.println(">>>>> ID: " + p.getId() + ", Name: " + p.getName());
        }
        System.out.println("---------------------------------------------------------------");
        /*End console log*/

        return "admin/product/show";
    }

    @GetMapping("/admin/product/{id}")
    public String getProductDetailPage(Model model, @PathVariable long id){
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);

        /*Console log*/
        System.out.println("----------------------View product detail-----------------------");
        System.out.println(">>>>> ID: " + product.getId());
        System.out.println(">>>>> Name: " + product.getName());
        System.out.println(">>>>> Quantity: " + product.getQuantity());
        System.out.println(">>>>> Price: " + product.getPrice());
        System.out.println(">>>>> Type: " + product.getType());
        System.out.println(">>>>> Factory: " + product.getFactory());
        System.out.println(">>>>> Short Desc: " + product.getShortDesc());
        System.out.println(">>>>> Detail Desc: " + product.getDetailDesc());
        System.out.println(">>>>> Image: " + product.getImage()); 
        System.out.println("---------------------------------------------------------------");
        /*End console log*/

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

        /*Console log*/
        System.out.println("--------------------------------------------------------------");
        System.out.println(">>>>> Name: " + product.getName());
        System.out.println(">>>>> Quantity: " + product.getQuantity());
        System.out.println(">>>>> Price: " + product.getPrice());
        System.out.println(">>>>> Type: " + product.getType());
        System.out.println(">>>>> Factory: " + product.getFactory());
        System.out.println(">>>>> Short Desc: " + product.getShortDesc());
        System.out.println(">>>>> Detail Desc: " + product.getDetailDesc());
        System.out.println(">>>>> Image: " + product.getImage()); 
        System.out.println("---------------------------------------------------------------");
        /*End console log*/

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

            /*Console log*/
            System.out.println("----------------------------------------------------");
            System.out.println(">>>>> ID: " + currentProduct.getId());
            System.out.println(">>>>> Name: " + currentProduct.getName() + ", Updated Name: " + updatedProduct.getName());
            System.out.println(">>>>> Quantity: " + currentProduct.getQuantity() + ", Updated Quantity: " + updatedProduct.getQuantity());
            System.out.println(">>>>> Price: " + currentProduct.getPrice() + ", Updated Price: " + updatedProduct.getPrice());
            System.out.println(">>>>> Type: " + currentProduct.getType() + ", Updated Type: " + updatedProduct.getType());
            System.out.println(">>>>> Factory: " + currentProduct.getFactory() + ", Updated Factory: " + updatedProduct.getFactory());
            System.out.println(">>>>> Short Desc: " + currentProduct.getShortDesc() + ", Updated Short Desc: " + updatedProduct.getShortDesc());
            System.out.println(">>>>> Detail Desc: " + currentProduct.getDetailDesc() + ", Updated Detail Desc: " + updatedProduct.getDetailDesc());
            System.out.println("----------------------------------------------------");
            /*End console log*/

            currentProduct.setName(updatedProduct.getName());
            currentProduct.setQuantity(updatedProduct.getQuantity());
            currentProduct.setPrice(updatedProduct.getPrice());
            currentProduct.setType(updatedProduct.getType());
            currentProduct.setFactory(updatedProduct.getFactory());
            currentProduct.setShortDesc(updatedProduct.getShortDesc());
            currentProduct.setDetailDesc(updatedProduct.getDetailDesc());

            if (!file.isEmpty()){

                /*Console log*/
                System.out.println(">>>>> Image: " + currentProduct.getImage() + "Updated Image: " + updatedProduct.getImage());
                System.out.println("----------------------------------------------------");
                /*End console log*/

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

        /*Console log*/
        System.out.println("----------------------------------------------------");
        System.out.println(">>>>> Delete product with ID = " + id);
        System.out.println("----------------------------------------------------");
        /*End console log*/

        return "redirect:/admin/product";
    }

}
