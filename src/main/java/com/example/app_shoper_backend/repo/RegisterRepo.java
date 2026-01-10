package com.example.app_shoper_backend.repo;

import com.example.app_shoper_backend.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepo extends JpaRepository<Register,Long> {
}
