package com.example.bejava_cmsbatdongsan.repository;

import com.example.bejava_cmsbatdongsan.entity.LoaiPhongBan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoaiPhongBanRepo extends JpaRepository<LoaiPhongBan, Integer> {
    Optional<LoaiPhongBan> findById(int integer);
}
