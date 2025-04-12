package com.tranthien.watchstore.controller.client;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.tranthien.watchstore.domain.User;
import com.tranthien.watchstore.domain.dto.RegisterDTO;
import com.tranthien.watchstore.service.UserService;

import jakarta.validation.Valid;

@Controller
public class HomePageController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public HomePageController(UserService userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String getHomePage(){
        return "client/homepage/index";
    }

    @GetMapping("/shop")
    public String getShoppingPage(){
        return "client/homepage/shop";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "client/auth/login";
    }

    @PostMapping("/login")
    public String handleLogin(){
        return "client/auth/login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("registerUser") @Valid RegisterDTO registerDTO, BindingResult bindingResult){
        
        if (bindingResult.hasErrors()){
            return "client/auth/register";
        }

        User user = this.userService.registerDTOtoUser(registerDTO);

        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName("USER"));

        // this.userService.handleSaveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/order-history")
    public String getHistoryPage(){
        return "client/cart/order-history";
    }

    @GetMapping("access-denied")
    public String getDenyPage(){
        return "client/auth/deny";
    }
}
