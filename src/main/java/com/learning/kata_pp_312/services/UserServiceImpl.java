package com.learning.kata_pp_312.services;

import com.learning.kata_pp_312.entities.Role;
import com.learning.kata_pp_312.entities.User;
import com.learning.kata_pp_312.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User: %s not found", username));
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                rolesToAuthority(user.getRoles()));
    }
    private Collection<? extends GrantedAuthority> rolesToAuthority(Collection<Role> roles) {
        return roles.stream().map(x -> new SimpleGrantedAuthority(x.getName())).collect(Collectors.toList());
    }
}
