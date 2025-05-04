package com.tranthien.watchstore.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public String getUserPage(Model model, 
        @RequestParam("page") Optional<String> pageOptional,
        @RequestParam("limit") Optional<String> limitOptional) {

        int page = 1;
        int limit = 10;
        try {
            if (pageOptional.isPresent()) {
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
        Page<User> userPage = this.userService.fetchUser(pageable);
        List<User> users = userPage.getContent();

        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", userPage.getTotalPages());
        model.addAttribute("limit", limit);

        /*Console log*/
        System.out.println("----------------------------------------------------");
        System.out.println(">>>>> Limit: " + limit + ", page: " + page);
        for (User user : users) {
            System.out.println(">>>>> ID: " + user.getId() + ", Name: " + user.getFullName() + ", Email: " + 
                user.getEmail());
        }
        System.out.println("----------------------------------------------------");
        /*End console log*/

        return "admin/user/show";
    }

    @GetMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id){
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);

        /*Console log*/
        System.out.println("----------------------View user detail-----------------------");
        System.out.println(">>>>> ID: " + user.getId());
        System.out.println(">>>>> Email: " + user.getEmail());
        System.out.println(">>>>> Fullname: " + user.getFullName());
        System.out.println(">>>>> Address: " + user.getAddress());
        System.out.println(">>>>> Phone: " + user.getPhone());
        System.out.println(">>>>> Role: " + user.getRole().getName());
        System.out.println(">>>>> Avatar: " + user.getAvatar());
        System.out.println("-------------------------------------------------------------");
        /*End console log*/

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
            System.out.println(">>>>>>>>>>> " + error.getField() + " - " + error.getDefaultMessage());
        }
        
        if (bindingResult.hasErrors()){
            return "admin/user/create";
        }

        if (this.userService.checkEmailExist(user.getEmail())){
            bindingResult.rejectValue("email", "", "Existed email!");

            /*Console log*/
            System.out.println(">>>>>>>>>>> Existed email!");
            /*End console log*/


            return "admin/user/create";
        }

        String avatar = this.uploadFileService.handleSaveUploadedFile(file, "avatar");
        Role role = this.userService.getRoleByName(user.getRole().getName());
        String hashedPassword = this.passwordEncoder.encode(user.getPassword());

        user.setRole(role);
        user.setAvatar(avatar);

        /*Console log*/
        System.out.println("----------------------------------------------------");
        System.out.println(">>>>> Fullname: " + user.getFullName());
        System.out.println(">>>>> Email: " + user.getEmail());
        System.out.println(">>>>> Password: " + user.getPassword());
        System.out.println(">>>>> Phone number: " + user.getPhone());
        System.out.println(">>>>> Address: " + user.getRole().getName());
        System.out.println(">>>>> Avatar: " + user.getAvatar());
        System.out.println("----------------------------------------------------");
        /*End console log*/

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

            /*Console log*/
            System.out.println("----------------------------------------------------");
            System.out.println(">>>>> ID: " + currentUser.getId());
            System.out.println(">>>>> Email: " + currentUser.getEmail() + ", Updated email: " + updatedUser.getEmail());
            System.out.println(">>>>> Fullname: " + currentUser.getFullName() + ", Updated Fullname: " + updatedUser.getFullName());
            System.out.println(">>>>> Address: " + currentUser.getAddress() + ", Updated Address: " + updatedUser.getAddress());
            System.out.println(">>>>> Phone: " + currentUser.getPhone() + ", Updated Phone: " + updatedUser.getPhone());
            System.out.println(">>>>> Role: " + currentUser.getRole().getName() + ", Updated Role: " + updatedUser.getRole().getName());
            /*End console log*/

            currentUser.setPhone(updatedUser.getPhone());
            currentUser.setAddress(updatedUser.getAddress());
            currentUser.setFullName(updatedUser.getFullName());
            currentUser.setRole(this.userService.getRoleByName(updatedUser.getRole().getName()));

            if (!file.isEmpty()) {

                /*Console log*/
                System.out.println(">>>>> Avatar: " + currentUser.getAvatar() + "Updated Avatar: " + updatedUser.getAvatar());
                System.out.println("----------------------------------------------------");
                /*End console log*/

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

        /*Console log*/
        System.out.println("----------------------------------------------------");
        System.out.println(">>>>> Delete user with ID = " + id);
        System.out.println("----------------------------------------------------");
        /*End console log*/

        return "redirect:/admin/user";
    }

}
