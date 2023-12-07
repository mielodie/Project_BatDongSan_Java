package com.example.bejava_cmsbatdongsan.repository;

import com.example.bejava_cmsbatdongsan.entity.NguoiDung;
import com.example.bejava_cmsbatdongsan.payload.dto.nguoidungdto.NguoiDungDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NguoiDungRepo extends JpaRepository<NguoiDung, Integer> {
    Optional<NguoiDung> findByTenTaiKhoan(String tenTaiKhoan);

    Optional<NguoiDung> findById(int id);

    Optional<NguoiDung> findByEmail(String email);

    Page<NguoiDungDTO> findAllByTenTaiKhoanLikeIgnoreCase(String username, PageRequest pageRequest);

    @Query(value = "SELECT COUNT(u) FROM NguoiDung u WHERE u.phongBanId = :phongBanId")
    int countUsersInTeam(@Param("phongBanId") int phongBanId);
}