package com.example.app_shoper_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "myShop")
public class MyShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shopName;
    private String location;
    private String email;
    private Long number;


    // shop register

    @OneToOne
    @JoinColumn(name = "register_id",unique = true)
    private Register register;

    // shop product
    @OneToMany(mappedBy = "myShop",cascade = CascadeType.ALL)
    private List<ShopProduct> shopProductList;
}
