package com.example.app_shoper_backend.repo;

import com.example.app_shoper_backend.entity.MyShop;
import com.example.app_shoper_backend.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyShopRepo extends JpaRepository<MyShop,Long> {
    MyShop findByRegisterId(Long id);

    MyShop findByRegister(Register register);
}
