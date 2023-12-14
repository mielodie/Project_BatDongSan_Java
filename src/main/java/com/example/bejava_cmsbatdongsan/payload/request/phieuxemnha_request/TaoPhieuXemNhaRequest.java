package com.example.bejava_cmsbatdongsan.payload.request.phieuxemnha_request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaoPhieuXemNhaRequest {
    private String tenKhachHang;
    private String soDienThoaiKH;
    private int khachHangId;
    private String moTa;
}
