package com.learning.kata_pp_312.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name="users")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        @Column(name = "name")
        private String name;
        @Column(name = "username")
        private String username;
        @Column(name = "password")
        private String password;

        @ManyToMany
        @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
        private Collection<Role> roles;
}
