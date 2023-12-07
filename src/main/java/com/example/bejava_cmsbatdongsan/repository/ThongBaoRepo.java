package com.example.bejava_cmsbatdongsan.repository;

import com.example.bejava_cmsbatdongsan.entity.ThongBao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThongBaoRepo extends JpaRepository<ThongBao, Integer> {
}
