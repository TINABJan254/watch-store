package com.tranthien.watchstore.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {
    
    @GetMapping("/product/{id}")
    public String getItemDetailPage(){
        return "client/item/detail";
    }
}
