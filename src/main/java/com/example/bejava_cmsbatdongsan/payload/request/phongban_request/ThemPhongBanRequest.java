package com.example.bejava_cmsbatdongsan.payload.request.phongban_request;

import lombok.Data;

import java.time.LocalDate;
@Data
public class ThemPhongBanRequest {
    private String code;
    private String tenPhongBan;
    private int thanhVien;
    private String moTa;
    private String khauHieu;
    private int truongPhongId;
}
