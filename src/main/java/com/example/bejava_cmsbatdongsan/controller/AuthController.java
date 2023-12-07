package com.example.bejava_cmsbatdongsan.controller;

import com.example.bejava_cmsbatdongsan.entity.NguoiDung;
import com.example.bejava_cmsbatdongsan.model.UserCustomDetail;
import com.example.bejava_cmsbatdongsan.payload.dto.nguoidungdto.NguoiDungDTO;
import com.example.bejava_cmsbatdongsan.payload.request.auth_request.*;
import com.example.bejava_cmsbatdongsan.payload.response.ResponseObject;
import com.example.bejava_cmsbatdongsan.payload.response.TokenResponse;
import com.example.bejava_cmsbatdongsan.repository.NguoiDungRepo;
import com.example.bejava_cmsbatdongsan.service.implement.AuthServiceImpl;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/")
public class AuthController {
    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private NguoiDungRepo nguoiDungRepo;

    @PostMapping("dangki")
    public ResponseObject<NguoiDungDTO> dangKi(@RequestBody DangKiRequest request) {
        return authService.dangKi(request);
    }

    @PostMapping("dangnhap")
    public TokenResponse dangNhap(@RequestBody DangNhapRequest request) {
        var result = authService.dangNhap(request);
        try {
            if (result == null) {
                throw new IllegalArgumentException("Không hợp lệ");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @PutMapping("doimatkhau")
    public ResponseEntity<String> doiMatKhau(@RequestBody DoiMatKhauRequest request) {
        try {
            UserCustomDetail nguoiDung = (UserCustomDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (nguoiDungRepo.findById(nguoiDung.getNguoiDung().getId()).isPresent()) {
                System.out.println("Nguoi dung la: " + nguoiDung.getNguoiDung().getTenTaiKhoan());
                var result = authService.doiMatKhau(nguoiDung.getNguoiDung().getId(), request);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Không tìm thấy thông tin người dùng.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok("Đổi mật khẩu thành công");
    }
    @PostMapping("quenmatkhau")
    public String quenMatKhau(@RequestBody QuenMatKhauRequest request){

        var result =  authService.quenMatKhau(request);
        try{
            if (result == null) {
                throw new IllegalArgumentException("Không hợp lệ");
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }
    @PutMapping("taomatkhaumoi")
    public ResponseObject<NguoiDungDTO> xacNhanTaoMatKhauMoi(@RequestBody XacNhanTaoMatKhauMoiRequest request){
        return authService.xacNhanTaoMatKhauMoi(request);
    }
}
