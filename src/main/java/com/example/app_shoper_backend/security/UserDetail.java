package com.example.app_shoper_backend.security;

import com.example.app_shoper_backend.entity.Register;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

public class UserDetail implements UserDetails {
    Register register;

    UserDetail( Register register){
        this.register = register;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(register.getRole().toString()));
    }

    @Override
    public  String getPassword() {
        return register.getPassword();
    }

    @Override
    public String getUsername() {
        return register.getMail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
