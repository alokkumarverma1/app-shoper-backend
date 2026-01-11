package com.example.app_shoper_backend.service;

import com.example.app_shoper_backend.dto.LoginDto;
import com.example.app_shoper_backend.dto.RegisterDto;
import com.example.app_shoper_backend.entity.Register;
import com.example.app_shoper_backend.repo.RegisterRepo;
import com.example.app_shoper_backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MainService {

    @Autowired
    private RegisterRepo registerRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;


    // register method
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

    // login mehtod
    public ResponseEntity<?> login(LoginDto loginDto){
       try{
           Authentication authentication =
                   authenticationManager.authenticate(
                           new UsernamePasswordAuthenticationToken(
                                   loginDto.getMail(),
                                   loginDto.getPassword()
                           )
                   );
       }catch (Exception e){
           throw  new UsernameNotFoundException("user not found");
       }

         String token = jwtUtil.generateToken(loginDto.getMail());
         Map<String,String> data = new HashMap<>();
         data.put("token", token);
         return ResponseEntity.ok().body(data);
    }





}
