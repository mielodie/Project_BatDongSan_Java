package com.example.bejava_cmsbatdongsan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "phongban_tbl")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhongBan extends BaseEntity{
    @Column(name = "maphongban")
    private String code;
    @Column(name = "tenphongban")
    private String tenPhongBan;
    @Column(name = "thanhvien")
    private int thanhVien;
    @Column(name = "mota")
    private String moTa;
    @Column(name = "sologan")
    private String khauHieu;
    @ManyToOne
    @JoinColumn(name = "loaiphongbanid", insertable = false, updatable = false)
    @JsonBackReference
    private LoaiPhongBan loaiPhongBan;
    @Column(name = "loaiphongbanid")
    private int loaiPhongBanId = 1;
    @Column(name = "thoigiantao")
    private LocalDate thoiGianTao = LocalDate.now();
    @Column(name = "thoigiancapnhat")
    private LocalDate thoiGianCapNhat = LocalDate.now();
    @Column(name = "truongphongid")
    private int truongPhongId;
    @OneToMany(mappedBy = "phongBan")
    @JsonManagedReference
    private Set<NguoiDung> nguoiDungs;
}
