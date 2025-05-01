package com.tranthien.watchstore.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.tranthien.watchstore.domain.Role;
import com.tranthien.watchstore.domain.User;
import com.tranthien.watchstore.domain.dto.RegisterDTO;
import com.tranthien.watchstore.repository.RoleRepository;
import com.tranthien.watchstore.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public User handleSaveUser(User user) {
        return this.userRepository.save(user);
    }

    public Role getRoleByName(String roleName) {
        return this.roleRepository.findByName(roleName);
    }

    public List<User> fetchUser(){
        return this.userRepository.findAll();
    }

    public User getUserById(long id){
        return this.userRepository.findById(id).get();
    }

    public void handleDeleteUserById(long id){
        this.userRepository.deleteById(id);
    }

    public boolean checkEmailExist(String email){
        return this.userRepository.existsByEmail(email);
    }

    public User getUserByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    public User registerDTOtoUser(RegisterDTO registerDTO){
        User user = new User();
        user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        return user;
    }

    public long countUsers() {
        return this.userRepository.count();
    }

}
