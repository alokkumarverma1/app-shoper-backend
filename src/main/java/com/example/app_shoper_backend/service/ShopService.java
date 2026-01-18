package com.example.app_shoper_backend.service;

import com.example.app_shoper_backend.configration.DefaultException;
import com.example.app_shoper_backend.dto.MyShopDto;
import com.example.app_shoper_backend.dto.ProductDto;
import com.example.app_shoper_backend.entity.MyShop;
import com.example.app_shoper_backend.entity.Register;
import com.example.app_shoper_backend.entity.ShopProduct;
import com.example.app_shoper_backend.repo.RegisterRepo;
import com.example.app_shoper_backend.repo.MyShopRepo;
import com.example.app_shoper_backend.repo.ShopProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.System.in;

@Service
public class ShopService {
    @Autowired
    private RegisterRepo registerRepo;
    @Autowired
    private MyShopRepo myShopRepo;
    @Autowired
    private ShopProductRepo shopProductRepo;




    // user shop create


    // user shop details
    public ResponseEntity<MyShopDto> myShop(Authentication authentication) {
        Register register = registerRepo.findByMail(authentication.getName());
        if(register == null){
            throw new DefaultException("user not found");
        }
        MyShop myShop = myShopRepo.findByRegisterId(register.getId());
        if(myShop == null){
           throw new DefaultException("shop not found");
        }
        MyShopDto myShopDto = new MyShopDto();
        myShopDto.setShopName(myShop.getShopName());
        myShopDto.setNumber(myShop.getNumber());
        myShopDto.setEmail(myShop.getEmail());
        myShopDto.setLocation(myShop.getLocation());
        return ResponseEntity.ok().body(myShopDto);
    }

// create shop
    public ResponseEntity<String> createShop(Authentication authentication,MyShopDto myShopDto){
       Register register = registerRepo.findByMail(authentication.getName());
        System.out.println("this is shop "+register.getName());
        MyShop myShop = new MyShop();
        myShop.setShopName(myShopDto.getShopName());
        myShop.setLocation(myShopDto.getLocation());
        myShop.setEmail(register.getMail());
        myShop.setNumber(register.getNumber());
        myShop.setRegister(register);
        myShopRepo.save(myShop);
        register.setMyShop(myShop);
        registerRepo.save(register);
        return ResponseEntity.ok().body("shop create success");
    }

    // add shop products in your shop

    public ResponseEntity<Map<String,Object>> addProduct(ProductDto productDto,Authentication authentication){
        Register register = registerRepo.findByMail(authentication.getName());
        if(register == null){
            throw new DefaultException("register not found");
        }
        MyShop myShop = myShopRepo.findByRegisterId(register.getId());
        if(myShop == null){
            throw new DefaultException("shop not foud");
        }
        ShopProduct shopProduct = new ShopProduct();
        shopProduct.setName(productDto.getName());
        shopProduct.setDetails(productDto.getDetails());
        shopProduct.setPrice(productDto.getPrice());
        shopProduct.setProductCount(productDto.getProductCount());
        shopProduct.setDiscount(productDto.getDiscount());
        shopProduct.setProductImage(productDto.getProductImage());
        shopProduct.setBrand(productDto.getBrand());
        shopProduct.setDate(LocalDate.now().toString());
        shopProduct.setCategory(productDto.getCategory());
        shopProduct.setMyShop(myShop);
        shopProductRepo.save(shopProduct);
        Map<String,Object> addProduct = new HashMap<>();
        addProduct.put("message","add product success");
        return ResponseEntity.ok().body(addProduct);
    }

    // get my shop product
    public ResponseEntity<Map<String,Object>> getAllShopProduct(Authentication authentication){
        Register register = registerRepo.findByMail(authentication.getName());
        if(register == null){
            throw new DefaultException("user not found");
        }
        MyShop myShop = myShopRepo.findByRegister(register);
        if(myShop== null){
            throw new DefaultException("shop not found");
        }
        List<ShopProduct> shopProduct = shopProductRepo.findAllByMyShop(myShop);
        if(shopProduct.isEmpty()){
            throw new DefaultException("no product found");
        }
        List<ProductDto> productDtos = shopProduct.stream().map(item->{
            ProductDto productDto = new ProductDto();
            productDto.setProductImage(item.getProductImage());
            productDto.setPrice(item.getPrice());
            productDto.setDiscount(item.getDiscount());
            productDto.setDetails(item.getDetails());
            productDto.setProductCount(item.getProductCount());
            productDto.setName(item.getName());
            productDto.setBrand(item.getBrand());
            productDto.setCategory(item.getCategory());
            productDto.setId(item.getId());
            productDto.setDate(item.getDate());
            return  productDto;
        }).collect(Collectors.toList());

        Map<String ,Object> data = new HashMap<>();
        data.put("message","product find success");
        data.put("data",productDtos);
        return ResponseEntity.ok().body(data);
    }

//     delete product of my shop
    public ResponseEntity<Map<String,Object>> deleteProduct(Long id,Authentication authentication){
        Register register = registerRepo.findByMail(authentication.getName());
        if(register == null){
            throw new DefaultException("user not found");
        }
        MyShop myShop = myShopRepo.findByRegister(register);
        if(myShop== null){
            throw new DefaultException("shop not found");
        }
        List<ShopProduct> product  = shopProductRepo.findAllByMyShop(myShop);
       for(ShopProduct productDto :product){
           if(productDto.getId().equals(id)){
              shopProductRepo.deleteById(id);
           }
       }
       Map<String,Object> res = new HashMap<>();
       res.put("message" , "product delete success");
       return ResponseEntity.ok().body(res);

    }

    // update product service




}
