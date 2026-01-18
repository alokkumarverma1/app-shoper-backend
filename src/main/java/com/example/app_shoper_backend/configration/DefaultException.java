package com.example.app_shoper_backend.configration;

import org.springframework.stereotype.Component;

public class DefaultException extends RuntimeException{


    public DefaultException(String message){
        super(message);
    }

}
