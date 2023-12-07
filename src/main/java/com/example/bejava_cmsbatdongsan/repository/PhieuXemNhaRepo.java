package com.example.bejava_cmsbatdongsan.repository;

import com.example.bejava_cmsbatdongsan.entity.PhieuXemNha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhieuXemNhaRepo extends JpaRepository<PhieuXemNha, Integer> {
}
