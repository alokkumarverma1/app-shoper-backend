package com.example.app_shoper_backend.service;

import com.example.app_shoper_backend.dto.RegisterDto;
import com.example.app_shoper_backend.entity.Register;
import com.example.app_shoper_backend.repo.RegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    @Autowired
    private RegisterRepo registerRepo;

    public ResponseEntity<?> register(RegisterDto registerDto){
        Register register = new Register();
        register.setName(registerDto.getName());
        register.setNumber(registerDto.getNumber());
        register.setMail(registerDto.getMail());
        register.setLocation(registerDto.getLocation());
        register.setPassword(registerDto.getPassword()); // first simple save kro
        registerRepo.save(register);
        return ResponseEntity.ok().body("register success");
    }
}
