package com.learning.kata_pp_312.service;

import com.learning.kata_pp_312.model.User;
import com.learning.kata_pp_312.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    
    public User findById(long id) {
        return userRepository.getOne(id);
    }

    
    public void updateUser(User user) {
        userRepository.save(user);
    }

    
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
