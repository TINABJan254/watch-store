package com.tranthien.watchstore.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    
    @GetMapping("/admin")
    public String index() {
        return "admin/dashboard/show";
    }
}
