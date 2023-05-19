package com.learning.kata_pp_312.service;

import com.learning.kata_pp_312.model.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();


    public User saveUser(User user);


    public User findById(long id);


    public void updateUser(User user);


    public void deleteUser(long id);
}
