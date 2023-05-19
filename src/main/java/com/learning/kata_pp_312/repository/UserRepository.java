package com.learning.kata_pp_312.repository;

import com.learning.kata_pp_312.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
