package com.example.bejava_cmsbatdongsan.service.iservice;

import com.example.bejava_cmsbatdongsan.payload.dto.nguoidungdto.NguoiDungDTO;
import com.example.bejava_cmsbatdongsan.payload.request.nguoidung_request.CapNhatThongTinNguoiDungRequest;
import com.example.bejava_cmsbatdongsan.payload.response.ResponseObject;
import org.springframework.data.domain.Page;

import java.util.Set;

public interface iNguoiDungService {
    ResponseObject<NguoiDungDTO> capNhatThongTinNguoiDung(CapNhatThongTinNguoiDungRequest request);
    String xoaNguoiDung(int nguoiDungId);
    Page<NguoiDungDTO> getAllNguoiDung(int pageNum, int pageSize);
    Page<NguoiDungDTO> getByTenTaiKhoan(String username, int pageNum, int pageSize);
    ResponseObject<NguoiDungDTO> getBySoDienThoai(String soDienThoai);
    ResponseObject<NguoiDungDTO> getByEmail(String email);
    ResponseObject<NguoiDungDTO> themNguoiDungVaoPhongBan(int nguoiDungId, int phongBanId);

}
