package com.example.app_shoper_backend.repo;

import com.example.app_shoper_backend.entity.MyShop;
import com.example.app_shoper_backend.entity.ShopProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopProductRepo extends JpaRepository<ShopProduct,Long> {
    ShopProduct findByMyShop(MyShop myShop);

    List<ShopProduct> findAllByMyShop(MyShop myShop);
}
