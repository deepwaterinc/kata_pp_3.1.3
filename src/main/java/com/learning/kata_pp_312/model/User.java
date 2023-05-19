package com.learning.kata_pp_312.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        @Column(name = "name")
        private String name;
        @Column(name = "surname")
        private String surname;
}
