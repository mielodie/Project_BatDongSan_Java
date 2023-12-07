package com.example.bejava_cmsbatdongsan.payload.request.auth_request;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data
public class DangKiRequest {
    private String tenTaiKhoan;
    private String matKhau;
    private String email;
    private String soDienThoai;
    private String tenNguoiDung;
    private LocalDate ngaySinh;
}
