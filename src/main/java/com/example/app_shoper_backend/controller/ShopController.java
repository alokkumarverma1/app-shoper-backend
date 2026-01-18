package com.example.app_shoper_backend.controller;

import com.example.app_shoper_backend.dto.MyShopDto;
import com.example.app_shoper_backend.dto.ProductDto;
import com.example.app_shoper_backend.repo.RegisterRepo;
import com.example.app_shoper_backend.repo.MyShopRepo;
import com.example.app_shoper_backend.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ShopController {


    @Autowired
    private MyShopRepo shopRepo;
    @Autowired
    private RegisterRepo registerRepo;
    @Autowired
    private ShopService shopService;

    @GetMapping("/myShop")
    public ResponseEntity<MyShopDto> myShop(Authentication authentication){
        ResponseEntity<MyShopDto> res = shopService.myShop(authentication);
        return  res;
    }

    // shop create controller
    @PostMapping("/createShop")
    public ResponseEntity<String> createShop(@RequestBody MyShopDto myShopDto,Authentication authentication){
        ResponseEntity<String> res = shopService.createShop(authentication,myShopDto);
        return res;
    }

    // add product in shop
    @PostMapping("/addProduct")
    public ResponseEntity<Map<String,Object>> addProduct(@RequestBody ProductDto productDto,Authentication authentication){
    ResponseEntity<Map<String,Object>> res = shopService.addProduct(productDto,authentication);
        return res;
    }

    // get all my product
    @GetMapping("/getAllShopProduct")
    public  ResponseEntity<Map<String,Object>> getAllShopProduct(Authentication authentication){
        ResponseEntity<Map<String,Object>> res= shopService.getAllShopProduct(authentication);
        return res;
    }

    // delete products
    @PostMapping("/deleteMyShopProduct/{id}")
    public ResponseEntity<Map<String,Object>> deleteProduct(@PathVariable Long id,Authentication authentication){
        ResponseEntity<Map<String,Object>> res = shopService.deleteProduct(id,authentication);
        return res;

    }



}
