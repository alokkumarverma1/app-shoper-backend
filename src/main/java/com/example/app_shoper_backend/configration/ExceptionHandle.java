package com.example.app_shoper_backend.configration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandle{


    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String,Object>> userNotFound(UsernameNotFoundException e){
       Map<String,Object> res = new HashMap<>();
       res.put("message","this g user not found");
       return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(DefaultException.class)
    public ResponseEntity<Map<String,Object>> handleGenericException(DefaultException e){
        Map<String,Object> res = new HashMap<>();
        res.put("message" , e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }
}
