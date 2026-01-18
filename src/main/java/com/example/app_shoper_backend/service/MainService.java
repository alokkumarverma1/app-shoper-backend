package com.example.app_shoper_backend.service;

import com.example.app_shoper_backend.dto.LoginDto;
import com.example.app_shoper_backend.dto.RegisterDto;
import com.example.app_shoper_backend.entity.Register;
import com.example.app_shoper_backend.entity.Role;
import com.example.app_shoper_backend.repo.RegisterRepo;
import com.example.app_shoper_backend.repo.RoleRepo;
import com.example.app_shoper_backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;


    // register method in service

    public ResponseEntity<Map<String,Object>> register(RegisterDto registerDto){
        Register register = registerRepo.findByMail(registerDto.getMail());
        Map<String,Object> res = new HashMap<>();
        if(register != null){
            res.put("success" , false );
            res.put("message" , "Email already registered");
            return ResponseEntity.badRequest().body(res);
        }
        register = new Register();
        register.setName(registerDto.getName());
        register.setNumber(registerDto.getNumber());
        register.setMail(registerDto.getMail());
        register.setLocation(registerDto.getLocation());
        register.setPassword(passwordEncoder.encode(registerDto.getPassword())); // first simple save kro
        Role role = roleRepo.findByRolename("USER");
        if(role== null){
            role = new Role();
            role.setRolename("USER");
        }
        register.setRole(role);
        roleRepo.save(role);
        registerRepo.save(register);
        res.put("success" , true );
        res.put("message" , "registration success");
        return ResponseEntity.ok().body(res);
    }

    // login mehtod
    public ResponseEntity<Map<String,Object>> userLogin(LoginDto loginDto){
        Authentication authentication;
        try{
            authentication =
                   authenticationManager.authenticate(
                           new UsernamePasswordAuthenticationToken(loginDto.getMail(), loginDto.getPassword()
                           )
                   );
       }catch (Exception e){
           throw  new UsernameNotFoundException("user not found");
       }
            String role = authentication.getAuthorities()
                .stream()
                .map(a -> a.getAuthority().replace("ROLE_", ""))
                .findFirst()
                .orElse("USER");
        String token = jwtUtil.generateToken(loginDto.getMail(),role);
         Map<String,Object> data = new HashMap<>();
         data.put("token", token);
         data.put("message" , "login sucess");
         return ResponseEntity.ok().body(data);
    }

   // user profile service
    public ResponseEntity<RegisterDto> userDetails(Authentication authentication){
        Register register = registerRepo.findByMail(authentication.getName());
        if(register == null){
            throw new UsernameNotFoundException("this user not found");
        };
        RegisterDto registerDto = new RegisterDto();
        registerDto.setName(register.getName());
        registerDto.setMail(register.getMail());
       registerDto.setNumber(register.getNumber());
       registerDto.setLocation(register.getLocation());
       return ResponseEntity.ok().body(registerDto);
    }





}
