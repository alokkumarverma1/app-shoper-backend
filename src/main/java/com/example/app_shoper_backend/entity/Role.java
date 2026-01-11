package com.example.app_shoper_backend.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String rolename;



    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    private List<Register> register;



}
