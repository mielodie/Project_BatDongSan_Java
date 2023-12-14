package com.example.bejava_cmsbatdongsan.service.iservice;

import com.example.bejava_cmsbatdongsan.payload.dto.phieuxemnhadto.PhieuXemNhaDTO;
import com.example.bejava_cmsbatdongsan.payload.request.phieuxemnha_request.SuaPhieuXemNhaRequest;
import com.example.bejava_cmsbatdongsan.payload.request.phieuxemnha_request.TaoPhieuXemNhaRequest;
import com.example.bejava_cmsbatdongsan.payload.response.ResponseObject;

import java.util.Set;

public interface iPhieuXemNhaService {
    ResponseObject<PhieuXemNhaDTO> taoPhieuXemNha(int sanPhamId, int nhanVienId, TaoPhieuXemNhaRequest request);
    ResponseObject<PhieuXemNhaDTO> suaPhieuXemNha(SuaPhieuXemNhaRequest request);
    String xoaPhieuXemNha(int phieuNhaId);
    Set<PhieuXemNhaDTO> getByTenKH(String tenKhachHang, int pageNum, int pageSize);
    Set<PhieuXemNhaDTO> getBySanPhamDaBan(boolean daBanThanhCong, int pageNum, int pageSize);
    Set<PhieuXemNhaDTO> getAllPXN(int pageNum, int pageSize);
}
