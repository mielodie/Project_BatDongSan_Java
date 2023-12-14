package com.example.bejava_cmsbatdongsan.payload.dto.nguoidungdto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class NguoiDungDTO {
    private int id;
    private String tenTaiKhoan;
    private String email;
    private String soDienThoai;
    private String tenNguoiDung;
    private LocalDate ngaySinh;
}
