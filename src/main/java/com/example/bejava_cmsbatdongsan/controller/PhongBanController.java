package com.example.bejava_cmsbatdongsan.controller;

import com.example.bejava_cmsbatdongsan.payload.dto.phongbandto.PhongBanDTO;
import com.example.bejava_cmsbatdongsan.payload.request.phongban_request.CapNhatThongTinPhongBanRequest;
import com.example.bejava_cmsbatdongsan.payload.request.phongban_request.ThemPhongBanRequest;
import com.example.bejava_cmsbatdongsan.payload.response.ResponseObject;
import com.example.bejava_cmsbatdongsan.service.implement.PhongBanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/phongban/")
public class PhongBanController {
    @Autowired
    private PhongBanService phongBanService;
    @PostMapping("themphongban")
    public ResponseObject<PhongBanDTO> themPhongBan(@RequestBody ThemPhongBanRequest request){
        return phongBanService.themPhongBan(request);
    }
    @PutMapping("capnhatthongtinphongban")
    public ResponseObject<PhongBanDTO> capNhatThongTinPhongBan(@RequestBody CapNhatThongTinPhongBanRequest request){
        return phongBanService.capNhatThongTinPhongBan(request);
    }
    @PutMapping("xoaphongban")
    public String xoaPhongBan(@RequestParam int phongBanId){
        return phongBanService.xoaPhongBan(phongBanId);
    }
    @GetMapping("laytatcaphongban")
    public List<PhongBanDTO> getAllPhongBan(@RequestParam int pageNum, @RequestParam int pageSize){
        return phongBanService.getAllPhongBan(pageNum, pageSize);
    }
    @GetMapping("layphongbantheotruongphong")
    public Set<PhongBanDTO> getByTruongPhong(@RequestParam int truongPhongId, @RequestParam int pageNum, @RequestParam int pageSize){
        return phongBanService.getByTruongPhong(truongPhongId, pageNum, pageSize);
    }
    @PostMapping("themtruongphongchophongban")
    public ResponseObject<PhongBanDTO> themTruongPhongVaoPhongBan(@RequestParam int phongBanId, @RequestParam int nguoiDungId){
        return phongBanService.themTruongPhongVaoPhongBan(phongBanId, nguoiDungId);
    }
    @PutMapping("thaydoitruongphongchophongban")
    public ResponseObject<PhongBanDTO> capNhatTruongPhongChoPhongBan(@RequestParam int truongPhongMoiId, @RequestParam int phongBanId){
        return phongBanService.capNhatTruongPhongChoPhongBan(truongPhongMoiId, phongBanId);
    }
}
