package com.demo.repositories;

import com.demo.entity.DichVu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DichVuRepo extends JpaRepository<DichVu, Integer> {
}