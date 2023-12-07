package com.example.bejava_cmsbatdongsan.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "loaisanpham_tbl")
@AllArgsConstructor
@NoArgsConstructor
public class LoaiSanPham extends BaseEntity{
    @Column(name = "maloaisanpham")
    private String code;
    @Column(name = "tenloaisanpham")
    private String tenLoaiSanPham;
    @OneToMany(mappedBy = "loaiSanPham")
    @JsonManagedReference
    private Set<SanPham> sanPhams;
}
