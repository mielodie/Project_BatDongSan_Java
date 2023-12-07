package com.example.bejava_cmsbatdongsan.service.iservice;

import com.example.bejava_cmsbatdongsan.payload.dto.nguoidungdto.NguoiDungDTO;
import com.example.bejava_cmsbatdongsan.payload.request.auth_request.*;
import com.example.bejava_cmsbatdongsan.payload.response.ResponseObject;
import com.example.bejava_cmsbatdongsan.payload.response.TokenResponse;
import org.springframework.http.ResponseEntity;

public interface iAuthService {
    ResponseObject<NguoiDungDTO> dangKi(DangKiRequest request);
    TokenResponse dangNhap(DangNhapRequest request);
    String doiMatKhau(int nguoiDungId, DoiMatKhauRequest request);
    String quenMatKhau(QuenMatKhauRequest request);
    ResponseObject<NguoiDungDTO> xacNhanTaoMatKhauMoi(XacNhanTaoMatKhauMoiRequest request);
}
