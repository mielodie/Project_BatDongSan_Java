package com.example.bejava_cmsbatdongsan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name = "nguoidung_tbl")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NguoiDung extends BaseEntity{
    @Column(name = "tentaikhoan")
    private String tenTaiKhoan;
    @Column(name = "matkhau")
    private String matKhau;
    private String email;
    @Column(name = "sodienthoai")
    private String soDienThoai;
    @Column(name = "tennguoidung")
    private String tenNguoiDung;
    @Column(name = "thoigiantao")
    private LocalDate thoiGianTao;
    @Column(name = "thoigiancapnhat")
    private LocalDate thoiGianCapNhat;
    @Column(name = "ngaysinh")
    private LocalDate ngaySinh;
    @ManyToOne
    @JoinColumn(name = "roleid", insertable = false, updatable = false)
    @JsonBackReference
    private Role role;
    @Column(name = "roleid")
    private int roleId;
    @ManyToOne
    @JoinColumn(name = "trangthainguoidungid", insertable = false, updatable = false)
    @JsonBackReference
    private TrangThaiNguoiDung trangThaiNguoiDung;
    @Column(name = "trangthainguoidungid")
    private int trangThaiNguoiDungId;
    @ManyToOne
    @JoinColumn(name = "phongbanid", insertable = false, updatable = false)
    @JsonBackReference
    private PhongBan phongBan;
    @Column(name = "phongbanid", nullable = true)
    private int phongBanId;
    private boolean isActive = true;
    @OneToMany(mappedBy = "nguoiTaoThongBao")
    @JsonManagedReference
    private Set<ThongBao> thongBaos;
    @OneToMany(mappedBy = "dauChu")
    @JsonManagedReference
    private Set<SanPham> sanPhams;
    @OneToMany(mappedBy = "nguoiDung")
    @JsonManagedReference
    private Set<XacNhanEmail> xacNhanEmails;

}
