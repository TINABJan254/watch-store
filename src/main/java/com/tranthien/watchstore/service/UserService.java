package com.tranthien.watchstore.service;

import org.springframework.stereotype.Service;

import com.tranthien.watchstore.domain.Role;
import com.tranthien.watchstore.domain.User;
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

}
