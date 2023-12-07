package com.example.bejava_cmsbatdongsan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "sanpham_tbl")
@AllArgsConstructor
@NoArgsConstructor
public class SanPham extends BaseEntity{
    @Column(name = "tenchunhadat")
    private String tenChuNhaDat;
    @Column(name = "sdtchunhadat")
    private String sdtChuNhaDat;
    @Column(name = "thoigianxaydung")
    private LocalDate thoiGianXayDung;
    @Column(name = "giaychungnhandat1")
    private String giayChungNhanDat1;
    @Column(name = "giaychungnhandat2")
    private String giaychungnhandat2;
    @ManyToOne
    @JoinColumn(name = "loaisanphamid", insertable = false, updatable = false)
    @JsonBackReference
    private LoaiSanPham loaiSanPham;
    @Column(name = "loaisanphamid")
    private int loaiSanPhamId = 2;
    @ManyToOne
    @JoinColumn(name = "dauchuid", insertable = false, updatable = false)
    @JsonBackReference
    private NguoiDung dauChu;
    @Column(name = "dauchuid")
    private int dauChuId;
    @Column(name = "thoidiemban")
    private LocalDate thoiDiemBan;
    @Column(name = "giaban")
    private double giaBan;
    @Column(name = "hoahong")
    private double hoaHong;
    @Column(name = "diachi")
    private String diaChi;
    @Column(name = "phantramchianhanvien")
    private double phanTramChiaNV;
    private boolean isActive = true;
    @OneToMany(mappedBy = "sanPham")
    @JsonManagedReference
    private Set<PhieuXemNha> phieuXemNhas;
    @OneToMany(mappedBy = "sanPham")
    @JsonManagedReference
    private Set<AnhSanPham> anhSanPhams;


}
