package com.example.app_shoper_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyShopDto {
    private String shopName;
    private String location;
    private String email;
    private Long number;
}
