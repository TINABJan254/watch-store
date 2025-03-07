package com.tranthien.watchstore.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    
    @GetMapping("/admin/user")
    public String getUserPage() {
        return "admin/user/show";
    }
}
