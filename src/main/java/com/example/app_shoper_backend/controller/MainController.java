package com.example.app_shoper_backend.controller;

import com.example.app_shoper_backend.dto.RegisterDto;
import com.example.app_shoper_backend.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private MainService mainService;

    @GetMapping("/start")
    public String connect(){
        return "connect success";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto){
        ResponseEntity<?> res = mainService.register(registerDto);
        return res;
    }




}
