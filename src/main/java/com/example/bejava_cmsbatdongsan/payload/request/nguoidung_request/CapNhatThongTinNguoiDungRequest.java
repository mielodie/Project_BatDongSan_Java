package com.example.bejava_cmsbatdongsan.payload.request.nguoidung_request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CapNhatThongTinNguoiDungRequest {
    private int nguoiDungId;
    private String email;
    private String soDienThoai;
    private String tenNguoiDung;
    private LocalDate ngaySinh;
}
