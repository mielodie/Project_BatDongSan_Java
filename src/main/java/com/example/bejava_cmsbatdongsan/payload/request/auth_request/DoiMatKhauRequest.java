package com.example.bejava_cmsbatdongsan.payload.request.auth_request;

import lombok.Data;

@Data
public class DoiMatKhauRequest {
    private String matKhauCu;
    private String matKhauMoi;
    private String xacNhanMatKhau;
}
