package com.learning.kata_pp_312.services;

import com.learning.kata_pp_312.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public List<User> findAll();


    public User saveUser(User user);


    public User findById(long id);


    public void updateUser(User user);


    public void deleteUser(long id);
}
