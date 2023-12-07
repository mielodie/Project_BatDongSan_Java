package com.example.bejava_cmsbatdongsan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "phieuxemnha_tbl")
@AllArgsConstructor
@NoArgsConstructor
public class PhieuXemNha extends BaseEntity{
    @Column(name = "tenkhachhang")
    private String tenKhachHang;
    @Column(name = "sdtkhachhang")
    private String sdtKhachHang;
    @Column(name = "khachhangid")
    private String khachHangId;
    @Column(name = "anhkhachhang1")
    private String anhKhachHang1;
    @Column(name = "anhkhachhang2")
    private String anhKhachHang2;
    @Column(name = "mota")
    private String moTa;
    @ManyToOne
    @JoinColumn(name = "sanphamid", insertable = false, updatable = false)
    @JsonBackReference
    private SanPham sanPham;
    @Column(name = "sanphamid")
    private int sanPhamId;
    @Column(name = "nhanvienid")
    private int nhanVienId;
    @Column(name = "thoigiantao")
    private LocalDate thoiGianTao;
    @Column(name = "banthanhcong")
    private boolean banThanhCong = false;
    private boolean isActive = true;
}
