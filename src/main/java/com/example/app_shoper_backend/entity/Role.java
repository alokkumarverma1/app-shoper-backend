package com.example.app_shoper_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role")
@ToString(exclude = "register")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String rolename;



    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
    private List<Register> register;




}
