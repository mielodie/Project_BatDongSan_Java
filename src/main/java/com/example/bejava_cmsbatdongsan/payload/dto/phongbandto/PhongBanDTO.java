package com.example.bejava_cmsbatdongsan.payload.dto.phongbandto;

import com.example.bejava_cmsbatdongsan.entity.NguoiDung;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class PhongBanDTO {
    private int id;
    private int loaiPhongBan;
    private String code;
    private String tenPhongBan;
    private int thanhVien;
    private String moTa;
    private String khauHieu;
    private LocalDate thoiGianTao;
    private LocalDate thoiGianCapNhat;
    private int truongPhongId;
    private Set<NguoiDung> nguoiDungs;
}
