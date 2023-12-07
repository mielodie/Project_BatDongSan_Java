package com.example.bejava_cmsbatdongsan.repository;

import com.example.bejava_cmsbatdongsan.entity.PhongBan;
import com.example.bejava_cmsbatdongsan.payload.dto.phongbandto.PhongBanDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PhongBanRepo extends JpaRepository<PhongBan, Integer> {
    Optional<PhongBan> findById(int integer);
    Set<PhongBanDTO> findAllByTruongPhongIdEqualsIgnoreCase(int truongPhongId, PageRequest pageRequest);
}
