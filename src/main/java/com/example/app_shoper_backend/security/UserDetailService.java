package com.example.app_shoper_backend.security;

import com.example.app_shoper_backend.entity.Register;
import com.example.app_shoper_backend.repo.RegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private RegisterRepo registerRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Register register =  registerRepo.findByMail(username);
        if(register == null){
            throw new  UsernameNotFoundException("user not found");
        }
        return new UserDetail(register);
    }
}
