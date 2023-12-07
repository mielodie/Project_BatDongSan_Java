package com.example.bejava_cmsbatdongsan.controller;

import com.example.bejava_cmsbatdongsan.payload.dto.nguoidungdto.NguoiDungDTO;
import com.example.bejava_cmsbatdongsan.payload.request.nguoidung_request.CapNhatThongTinNguoiDungRequest;
import com.example.bejava_cmsbatdongsan.payload.response.ResponseObject;
import com.example.bejava_cmsbatdongsan.service.implement.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nguoidung/")
public class NguoiDungController {
    @Autowired
    private NguoiDungService nguoiDungService;
    @PutMapping("permit/capnhatthongtinnguoidung")
    public ResponseObject<NguoiDungDTO> capNhatThongTinNguoiDung(@RequestBody CapNhatThongTinNguoiDungRequest request){
        return nguoiDungService.capNhatThongTinNguoiDung(request);
    }
    @PutMapping("permit/xoanguoidung")
    public String xoaNguoiDung(@RequestParam int nguoiDungId){
        return nguoiDungService.xoaNguoiDung(nguoiDungId);
    }
    @GetMapping("permit/laytatcanguoidung")
    public Page<NguoiDungDTO> getAllNguoiDung(@RequestParam int pageNum, @RequestParam int pageSize){
        return nguoiDungService.getAllNguoiDung(pageNum, pageSize);
    }
    @GetMapping("permit/laynguoidungtheotentaikhoan")
    public Page<NguoiDungDTO> getByTenTaiKhoan(@RequestParam String username, @RequestParam int pageNum, @RequestParam int pageSize){
        return nguoiDungService.getByTenTaiKhoan(username, pageNum, pageSize);
    }
    @GetMapping("permit/laynguoidungtheosodienthoai")
    public ResponseObject<NguoiDungDTO> getBySoDienThoai(@RequestParam String soDienThoai){
        return nguoiDungService.getBySoDienThoai(soDienThoai);
    }
    @GetMapping("permit/laynguoidungtheoemail")
    public ResponseObject<NguoiDungDTO> getByEmail(@RequestParam String email){
        return nguoiDungService.getByEmail(email);
    }
    @PutMapping("add/themnguoidungvaophongban")
    public ResponseObject<NguoiDungDTO> themNguoiDungVaoPhongBan(@RequestParam int nguoiDungId, @RequestParam int phongBanId){
        return nguoiDungService.themNguoiDungVaoPhongBan(nguoiDungId, phongBanId);
    }
}
