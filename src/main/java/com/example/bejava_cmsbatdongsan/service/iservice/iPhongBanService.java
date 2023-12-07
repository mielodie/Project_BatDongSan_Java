package com.example.bejava_cmsbatdongsan.service.iservice;

import com.example.bejava_cmsbatdongsan.payload.dto.phongbandto.PhongBanDTO;
import com.example.bejava_cmsbatdongsan.payload.request.phongban_request.CapNhatThongTinPhongBanRequest;
import com.example.bejava_cmsbatdongsan.payload.request.phongban_request.ThemPhongBanRequest;
import com.example.bejava_cmsbatdongsan.payload.response.ResponseObject;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface iPhongBanService {
    ResponseObject<PhongBanDTO> themPhongBan(ThemPhongBanRequest request);
    ResponseObject<PhongBanDTO> capNhatThongTinPhongBan(CapNhatThongTinPhongBanRequest request);
    String xoaPhongBan(int phongBanId);
    List<PhongBanDTO> getAllPhongBan(int pageNum, int pageSize);
    Set<PhongBanDTO> getByTruongPhong(int truongPhongId, int pageNum, int pageSize);
    ResponseObject<PhongBanDTO> themTruongPhongVaoPhongBan(int phongBanId, int nguoiDungId);
    ResponseObject<PhongBanDTO> capNhatTruongPhongChoPhongBan(int truongPhongMoiId, int phongBanId);


}
