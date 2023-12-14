package com.example.bejava_cmsbatdongsan.service.implement;

import com.example.bejava_cmsbatdongsan.entity.NguoiDung;
import com.example.bejava_cmsbatdongsan.entity.PhieuXemNha;
import com.example.bejava_cmsbatdongsan.entity.SanPham;
import com.example.bejava_cmsbatdongsan.handle.helper_handle.ChuanHoaChuoi;
import com.example.bejava_cmsbatdongsan.payload.converter.PhieuXemNhaConverter;
import com.example.bejava_cmsbatdongsan.payload.dto.phieuxemnhadto.PhieuXemNhaDTO;
import com.example.bejava_cmsbatdongsan.payload.request.phieuxemnha_request.SuaPhieuXemNhaRequest;
import com.example.bejava_cmsbatdongsan.payload.request.phieuxemnha_request.TaoPhieuXemNhaRequest;
import com.example.bejava_cmsbatdongsan.payload.response.ResponseObject;
import com.example.bejava_cmsbatdongsan.repository.NguoiDungRepo;
import com.example.bejava_cmsbatdongsan.repository.PhieuXemNhaRepo;
import com.example.bejava_cmsbatdongsan.repository.SanPhamRepo;
import com.example.bejava_cmsbatdongsan.service.iservice.iPhieuXemNhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PhieuXemNhaService implements iPhieuXemNhaService {
    @Autowired
    private SanPhamRepo sanPhamRepo;
    @Autowired
    private NguoiDungRepo nguoiDungRepo;
    @Autowired
    private PhieuXemNhaRepo phieuXemNhaRepo;
    @Autowired
    private ResponseObject<PhieuXemNhaDTO> phieuXemNhaDTOResponseObject;
    @Autowired
    private PhieuXemNhaConverter phieuXemNhaConverter;
    @Autowired
    private ChuanHoaChuoi chuanHoaChuoi;
    public ResponseObject<PhieuXemNhaDTO> taoPhieuXemNha(int sanPhamId, int nhanVienId, TaoPhieuXemNhaRequest request) {
        Optional<SanPham> sanPhamCheck = sanPhamRepo.findById(sanPhamId);
        if(sanPhamCheck.isEmpty()){
            return phieuXemNhaDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_NOT_FOUND,
                    "Sản phẩm này không tồn tại trên hệ thống",
                    null
            );
        }
        Optional<NguoiDung> nguoiDungCheck = nguoiDungRepo.findById(sanPhamId);
        if(nguoiDungCheck.isEmpty()){
            return phieuXemNhaDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_NOT_FOUND,
                    "Người dùng này không tồn tại trên hệ thống",
                    null
            );
        }
        PhieuXemNha newPhieuXemNha = PhieuXemNha
                .builder()
                .banThanhCong(false)
                .nhanVienId(nhanVienId)
                .sanPhamId(sanPhamId)
                .thoiGianTao(LocalDate.now())
                .khachHangId(request.getKhachHangId())
                .sdtKhachHang(request.getSoDienThoaiKH())
                .moTa(request.getMoTa())
                .tenKhachHang(request.getTenKhachHang())
                .build();
        phieuXemNhaRepo.save(newPhieuXemNha);
        return phieuXemNhaDTOResponseObject.responseSuccess(
                "Tạo phiếu xem nhà thành công",
                phieuXemNhaConverter.entityToDTO(newPhieuXemNha)
        );
    }

    public ResponseObject<PhieuXemNhaDTO> suaPhieuXemNha(SuaPhieuXemNhaRequest request) {
        Optional<PhieuXemNha> phieuXemNhaCheck = phieuXemNhaRepo.findById(request.getId());
        if(phieuXemNhaCheck.isEmpty()){
            return phieuXemNhaDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_NOT_FOUND,
                    "Phiếu xem nhà này không tồn tại trên hệ thống",
                    null
            );
        }
        phieuXemNhaCheck.get().setMoTa(request.getMoTa());
        phieuXemNhaCheck.get().setSdtKhachHang(request.getSoDienThoaiKH());
        phieuXemNhaCheck.get().setKhachHangId(request.getKhachHangId());
        phieuXemNhaCheck.get().setTenKhachHang(request.getTenKhachHang());
        phieuXemNhaCheck.get().setBanThanhCong(request.isBanThanhCong());
        phieuXemNhaRepo.save(phieuXemNhaCheck.get());
        return phieuXemNhaDTOResponseObject.responseSuccess(
                "Cập nhật thông tin phiếu xem nhà thành công",
                phieuXemNhaConverter.entityToDTO(phieuXemNhaCheck.get())
        );
    }

    public String xoaPhieuXemNha(int phieuNhaId) {
        Optional<PhieuXemNha> phieuXemNhaCheck = phieuXemNhaRepo.findById(phieuNhaId);
        if(phieuXemNhaCheck.isEmpty()){
            return "Phiếu xem nhà này không tồn tại trên hệ thống";
        }
        phieuXemNhaRepo.delete(phieuXemNhaCheck.get());
        return "Xóa phiếu xem nhà thành công";
    }

    public Set<PhieuXemNhaDTO> getByTenKH(String tenKhachHang, int pageNum, int pageSize) {
        return phieuXemNhaRepo
                .findAll()
                .stream()
                .filter(x -> chuanHoaChuoi.chuanHoaChuoi(x.getTenKhachHang()).contains(chuanHoaChuoi.chuanHoaChuoi(tenKhachHang)))
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .map(x -> phieuXemNhaConverter.entityToDTO(x))
                .collect(Collectors.toSet());
    }

    public Set<PhieuXemNhaDTO> getBySanPhamDaBan(boolean daBanThanhCong, int pageNum, int pageSize) {
        return phieuXemNhaRepo
                .findAll()
                .stream()
                .filter(x -> x.isBanThanhCong() == daBanThanhCong)
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .map(x -> phieuXemNhaConverter.entityToDTO(x))
                .collect(Collectors.toSet());
    }

    public Set<PhieuXemNhaDTO> getAllPXN(int pageNum, int pageSize) {
        return phieuXemNhaRepo
                .findAll()
                .stream()
                .skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .map(x -> phieuXemNhaConverter.entityToDTO(x))
                .collect(Collectors.toSet());
    }
}
