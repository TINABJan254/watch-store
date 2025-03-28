package com.tranthien.watchstore.controller.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.tranthien.watchstore.domain.Role;
import com.tranthien.watchstore.domain.User;
import com.tranthien.watchstore.service.UploadFileService;
import com.tranthien.watchstore.service.UserService;

import jakarta.validation.Valid;

@Controller
public class UserController {

    private final UploadFileService uploadFileService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    
    public UserController(UploadFileService uploadFileService, UserService userService, PasswordEncoder passwordEncoder) {
        this.uploadFileService = uploadFileService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.fetchUser();
        model.addAttribute("users", users);
        return "admin/user/show";
    }

    @GetMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id){
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        return "admin/user/detail";
    }

    @GetMapping("/admin/user/create")
    public String getCreatePage(Model model){
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping("/admin/user/create")
    public String createUser(@ModelAttribute("newUser") @Valid User user, BindingResult bindingResult, @RequestParam("avatarImg") MultipartFile file){
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>>>>>>>>>" + error.getField() + " - " + error.getDefaultMessage());
        }
        
        if (bindingResult.hasErrors()){
            return "admin/user/create";
        }

        if (this.userService.checkEmailExist(user.getEmail())){
            bindingResult.rejectValue("email", "", "Existed email!");
            return "admin/user/create";
        }

        String avatar = this.uploadFileService.handleSaveUploadedFile(file, "avatar");
        Role role = this.userService.getRoleByName(user.getRole().getName());
        String hashedPassword = this.passwordEncoder.encode(user.getPassword());

        user.setRole(role);
        user.setAvatar(avatar);
        user.setPassword(hashedPassword);

        this.userService.handleSaveUser(user);

        return "redirect:/admin/user";
    }

    @GetMapping("admin/user/update/{id}")
    public String getUpdatePage(Model model, @PathVariable long id){
        User user = this.userService.getUserById(id);
        model.addAttribute("newUser", user);
        return "admin/user/update";
    }

    @PostMapping("admin/user/update")
    public String updateUser(Model model, @ModelAttribute("newUser")User updatedUser, BindingResult bindingResult, @RequestParam("avatarImg") MultipartFile file){
        
        User currentUser = this.userService.getUserById(updatedUser.getId());

        if (currentUser != null){
            currentUser.setPhone(updatedUser.getPhone());
            currentUser.setAddress(updatedUser.getAddress());
            currentUser.setFullName(updatedUser.getFullName());
            currentUser.setRole(this.userService.getRoleByName(updatedUser.getRole().getName()));

            if (!file.isEmpty()) {
                // Xử lý upload file và cập nhật avatar
                String avatar = this.uploadFileService.handleSaveUploadedFile(file, "avatar");
                currentUser.setAvatar(avatar); // Cập nhật avatar mới
            }

            this.userService.handleSaveUser(currentUser);
        }

        
        return "redirect:/admin/user";
    }

    @PostMapping("/admin/user/delete")
    public String deleteUser(@RequestParam("id") Long id){
        this.userService.handleDeleteUserById(id);
        return "redirect:/admin/user";
    }

}
