package com.example.bejava_cmsbatdongsan.payload.request.phongban_request;

import lombok.Data;

@Data
public class CapNhatThongTinPhongBanRequest {
    private int phongBanId;
    private String tenPhongBan;
    private String moTa;
    private String khauHieu;
}
