package com.learning.kata_pp_312.repositories;

import com.learning.kata_pp_312.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
