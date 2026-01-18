package com.example.app_shoper_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String productImage;
    private String name;
    private String details;
    private int price;
    private int discount;
    private int productCount;
    private String category;
    private String brand;
    private String date;
}
