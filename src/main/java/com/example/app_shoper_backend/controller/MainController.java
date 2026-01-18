package com.example.app_shoper_backend.controller;

import com.example.app_shoper_backend.dto.LoginDto;
import com.example.app_shoper_backend.dto.RegisterDto;
import com.example.app_shoper_backend.service.MainService;
import com.example.app_shoper_backend.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MainController {

    @Autowired
    private MainService mainService;

    // register method
    @PostMapping("/userRegister")
    public ResponseEntity<Map<String,Object>> register(@RequestBody RegisterDto registerDto){
        ResponseEntity<Map<String,Object>> res = mainService.register(registerDto);
        return res;
    }

    // login method
    @PostMapping("/userLogin")
    public ResponseEntity<Map<String,Object>> login(@RequestBody LoginDto loginDto){
        ResponseEntity<Map<String,Object>> res = mainService.userLogin(loginDto);
        return res;
    }


    // user profile
    @GetMapping("/userDetails")
    public ResponseEntity<RegisterDto> userDetaild(Authentication authentication){
        System.out.println("Authentication object: " + authentication);
        if(authentication != null){
            System.out.println("Username: " + authentication.getName());
        }
        ResponseEntity<RegisterDto> res = mainService.userDetails(authentication);
        return res;
    }




}
