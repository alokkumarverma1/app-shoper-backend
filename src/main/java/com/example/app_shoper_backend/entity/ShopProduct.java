package com.example.app_shoper_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shopProduct")
public class ShopProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String productImage;
    private String name;
    private String details;
    private int price;
    private int discount;
    private int productCount;
    private String category;
    private String brand;
    private String date;

    @ManyToOne
    @JoinColumn(name = "myShop_id")
    private MyShop myShop;
}
