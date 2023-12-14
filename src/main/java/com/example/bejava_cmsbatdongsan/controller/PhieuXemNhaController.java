package com.example.bejava_cmsbatdongsan.controller;

import com.example.bejava_cmsbatdongsan.payload.dto.phieuxemnhadto.PhieuXemNhaDTO;
import com.example.bejava_cmsbatdongsan.payload.request.phieuxemnha_request.SuaPhieuXemNhaRequest;
import com.example.bejava_cmsbatdongsan.payload.request.phieuxemnha_request.TaoPhieuXemNhaRequest;
import com.example.bejava_cmsbatdongsan.payload.response.ResponseObject;
import com.example.bejava_cmsbatdongsan.service.implement.PhieuXemNhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/phieuxemnha/")
public class PhieuXemNhaController {
    @Autowired
    private PhieuXemNhaService phieuXemNhaService;
    @PostMapping("taophieuxemnha")
    public ResponseObject<PhieuXemNhaDTO> taoPhieuXemNha(@RequestParam int sanPhamId, @RequestParam int nhanVienId, @RequestBody TaoPhieuXemNhaRequest request){
        return phieuXemNhaService.taoPhieuXemNha(sanPhamId, nhanVienId, request);
    }
    @PostMapping("suaphieuxemnha")
    public ResponseObject<PhieuXemNhaDTO> suaPhieuXemNha(@RequestBody SuaPhieuXemNhaRequest request){
        return phieuXemNhaService.suaPhieuXemNha(request);
    }
    @PostMapping("xoaphieuxemnha")
    public String xoaPhieuXemNha(@RequestParam int phieuNhaId){
        return phieuXemNhaService.xoaPhieuXemNha(phieuNhaId);
    }
    @PostMapping("laypxntheotenkhachhang")
    public Set<PhieuXemNhaDTO> getByTenKH(@RequestParam String tenKhachHang, @RequestParam int pageNum, @RequestParam int pageSize){
        return phieuXemNhaService.getByTenKH(tenKhachHang, pageNum, pageSize);
    }
    @PostMapping("laypxntheosanphamdaban")
    public Set<PhieuXemNhaDTO> getBySanPhamDaBan(@RequestParam boolean daBanThanhCong, @RequestParam int pageNum, @RequestParam int pageSize){
        return phieuXemNhaService.getBySanPhamDaBan(daBanThanhCong, pageNum, pageSize);
    }
    @PostMapping("laytatcaphieuxemnha")
    public Set<PhieuXemNhaDTO> getAllPXN(@RequestParam int pageNum, @RequestParam int pageSize){
        return phieuXemNhaService.getAllPXN(pageNum, pageSize);
    }
}
