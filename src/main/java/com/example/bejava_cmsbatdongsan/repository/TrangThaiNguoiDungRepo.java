package com.example.bejava_cmsbatdongsan.repository;

import com.example.bejava_cmsbatdongsan.entity.TrangThaiNguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrangThaiNguoiDungRepo extends JpaRepository<TrangThaiNguoiDung, Integer> {
}
