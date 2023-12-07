package com.example.bejava_cmsbatdongsan.payload.converter;

import com.example.bejava_cmsbatdongsan.entity.NguoiDung;
import com.example.bejava_cmsbatdongsan.payload.dto.nguoidungdto.NguoiDungDTO;
import org.springframework.stereotype.Component;

@Component
public class NguoiDungConverter {
    public NguoiDungDTO entityToDTO(NguoiDung nguoiDung){
        NguoiDungDTO dto = new NguoiDungDTO();
        dto.setTenTaiKhoan(nguoiDung.getTenTaiKhoan());
        dto.setTenNguoiDung(nguoiDung.getTenNguoiDung());
        dto.setEmail(nguoiDung.getEmail());
        dto.setNgaySinh(nguoiDung.getNgaySinh());
        dto.setSoDienThoai(nguoiDung.getSoDienThoai());
        return dto;
    }
}
