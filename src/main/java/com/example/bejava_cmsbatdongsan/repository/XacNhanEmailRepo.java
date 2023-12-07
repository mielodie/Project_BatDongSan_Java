package com.example.bejava_cmsbatdongsan.repository;

import com.example.bejava_cmsbatdongsan.entity.XacNhanEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface XacNhanEmailRepo extends JpaRepository<XacNhanEmail, Integer> {
    Optional<XacNhanEmail> findByCode(String code);
}
