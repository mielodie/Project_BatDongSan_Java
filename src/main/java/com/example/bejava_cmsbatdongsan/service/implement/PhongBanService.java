package com.example.bejava_cmsbatdongsan.service.implement;

import com.example.bejava_cmsbatdongsan.entity.NguoiDung;
import com.example.bejava_cmsbatdongsan.entity.PhongBan;
import com.example.bejava_cmsbatdongsan.enumeration.RoleEnums;
import com.example.bejava_cmsbatdongsan.payload.converter.NguoiDungConverter;
import com.example.bejava_cmsbatdongsan.payload.converter.PhongBanConverter;
import com.example.bejava_cmsbatdongsan.payload.dto.phongbandto.PhongBanDTO;
import com.example.bejava_cmsbatdongsan.payload.request.phongban_request.CapNhatThongTinPhongBanRequest;
import com.example.bejava_cmsbatdongsan.payload.request.phongban_request.ThemPhongBanRequest;
import com.example.bejava_cmsbatdongsan.payload.response.ResponseObject;
import com.example.bejava_cmsbatdongsan.repository.LoaiPhongBanRepo;
import com.example.bejava_cmsbatdongsan.repository.NguoiDungRepo;
import com.example.bejava_cmsbatdongsan.repository.PhongBanRepo;
import com.example.bejava_cmsbatdongsan.service.iservice.iPhongBanService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PhongBanService implements iPhongBanService {
    @Autowired
    private ResponseObject<PhongBanDTO> phongBanDTOResponseObject;
    @Autowired
    private NguoiDungRepo nguoiDungRepo;
    @Autowired
    private PhongBanRepo phongBanRepo;
    @Autowired
    private PhongBanConverter phongBanConverter;

    public ResponseObject<PhongBanDTO> themPhongBan(ThemPhongBanRequest request) {
        if (StringUtils.isBlank(request.getTenPhongBan())
                || StringUtils.isBlank(request.getCode())
                || request.getTruongPhongId() == 0
                || StringUtils.isBlank(request.getMoTa())
        ) {
            return phongBanDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "Vui lòng điền đầy đủ thông tin",
                    null
            );
        }
        var checkTruongPhong = nguoiDungRepo.findById(request.getTruongPhongId());
        if(checkTruongPhong.isEmpty()){
            return phongBanDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_NOT_FOUND,
                    "Trưởng phòng có id " + request.getTruongPhongId() + " không tồn tại",
                    null
            );
        }
        if(!checkTruongPhong.get().getRole().getTenQuyenHan().equals(RoleEnums.MANAGER)){
            return phongBanDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_NOT_FOUND,
                    "Người dùng này quyền không hợp lệ",
                    null
            );
        }
        try {
            PhongBan phongBan = PhongBan
                    .builder()
                    .loaiPhongBanId(1)
                    .tenPhongBan(request.getTenPhongBan())
                    .code(request.getCode())
                    .moTa(request.getMoTa())
                    .khauHieu(request.getKhauHieu())
                    .thanhVien(0)
                    .truongPhongId(request.getTruongPhongId())
                    .thoiGianTao(LocalDate.now())
                    .thoiGianCapNhat(LocalDate.now())
                    .build();
            phongBanRepo.save(phongBan);
            PhongBanDTO phongBanDTO = phongBanConverter.entityToDTO(phongBan);
            phongBanDTO.setNguoiDungs(
                    nguoiDungRepo
                            .findAll()
                            .stream()
                            .filter(x -> x.getPhongBanId() == phongBan.getId())
                            .collect(Collectors.toSet()));
            return phongBanDTOResponseObject.responseSuccess(
                    "Thêm phòng ban thành công",
                    phongBanDTO
            );
        }
        catch (Exception e){
            return phongBanDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_INTERNAL_ERROR,
                    e.getMessage(),
                    null
            );
        }
    }

    public ResponseObject<PhongBanDTO> capNhatThongTinPhongBan(CapNhatThongTinPhongBanRequest request) {
        var checkPhongBan = phongBanRepo.findById(request.getPhongBanId());
        if(checkPhongBan.isEmpty()){
            return phongBanDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_NOT_FOUND,
                    "Phòng ban này không tồn tại trên hệ thống",
                    null
            );
        }
        checkPhongBan.get().setThoiGianCapNhat(LocalDate.now());
        checkPhongBan.get().setTenPhongBan(request.getTenPhongBan());
        checkPhongBan.get().setThoiGianTao(LocalDate.now());
        checkPhongBan.get().setThanhVien(nguoiDungRepo.countUsersInTeam(checkPhongBan.get().getId()));
        checkPhongBan.get().setMoTa(request.getMoTa());
        checkPhongBan.get().setKhauHieu(request.getKhauHieu());
        phongBanRepo.save(checkPhongBan.get());
        return phongBanDTOResponseObject.responseSuccess(
                "Cập nhật thông tin phòng ban thành công",
                phongBanConverter.entityToDTO(checkPhongBan.get())
        );
    }

    public String xoaPhongBan(int phongBanId) {
        var checkPhongBan = phongBanRepo.findById(phongBanId);
        if(checkPhongBan.isEmpty()){
            return "Phòng ban đang không tồn tại hoặc đã ngưng hoạt động";
        }
        checkPhongBan.get().setLoaiPhongBanId(2);
        phongBanRepo.save(checkPhongBan.get());
        return "Ngưng hoạt động phòng ban thành công";
    }

    public List<PhongBanDTO> getAllPhongBan(int pageNum, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        var p = phongBanRepo
                .findAll(pageRequest)
                        .map(phongBanConverter::entityToDTO);
        p.forEach(System.out::println);
        return p.stream().collect(Collectors.toList());
    }

    public Set<PhongBanDTO> getByTruongPhong(int truongPhongId, int pageNum, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        return phongBanRepo
                .findAllByTruongPhongIdEqualsIgnoreCase(truongPhongId, pageRequest)
                //.getContent()
                .stream().collect(Collectors.toSet());
    }

    public ResponseObject<PhongBanDTO> themTruongPhongVaoPhongBan(int phongBanId, int nguoiDungId) {
        Optional<NguoiDung> nguoiDung = nguoiDungRepo.findById(nguoiDungId);
        if(nguoiDung.isEmpty()){
            return phongBanDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_NOT_FOUND,
                    "Không tìm thấy người dùng",
                    null
            );
        }
        if(!nguoiDung.get().getRole().getTenQuyenHan().equals(RoleEnums.MANAGER)){
            return phongBanDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_NOT_FOUND,
                    "Người dùng này quyền không hợp lệ",
                    null
            );
        }
        Optional<PhongBan> phongBan = phongBanRepo.findById(phongBanId);
        if(phongBan.isEmpty()){
            return phongBanDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_NOT_FOUND,
                    "Không tìm thấy phòng ban",
                    null
            );
        }
        phongBan.get().setTruongPhongId(nguoiDungId);
        nguoiDung.get().setPhongBanId(phongBanId);
        phongBan.get().setLoaiPhongBanId(1);
        phongBanRepo.save(phongBan.get());
        nguoiDungRepo.save(nguoiDung.get());
        return phongBanDTOResponseObject.responseSuccess(
                "Thêm trưởng phòng cho phòng ban thành công",
                phongBanConverter.entityToDTO(phongBan.get())
        );
    }

    public ResponseObject<PhongBanDTO> capNhatTruongPhongChoPhongBan(int truongPhongMoiId, int phongBanId) {
        Optional<NguoiDung> nguoiDung = nguoiDungRepo.findById(truongPhongMoiId);
        if(nguoiDung.isEmpty()){
            return phongBanDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_NOT_FOUND,
                    "Không tìm thấy người dùng",
                    null
            );
        }
        if(!nguoiDung.get().getRole().getTenQuyenHan().equals(RoleEnums.MANAGER)){
            return phongBanDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_NOT_FOUND,
                    "Người dùng này quyền không hợp lệ",
                    null
            );
        }
        Optional<PhongBan> phongBan = phongBanRepo.findById(phongBanId);
        if(phongBan.isEmpty()){
            return phongBanDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_NOT_FOUND,
                    "Không tìm thấy phòng ban",
                    null
            );
        }
        phongBan.get().setTruongPhongId(truongPhongMoiId);
        nguoiDung.get().setPhongBanId(phongBanId);
        phongBanRepo.save(phongBan.get());
        nguoiDungRepo.save(nguoiDung.get());
        return phongBanDTOResponseObject.responseSuccess(
                "Thay đổi trưởng phòng thành công",
                phongBanConverter.entityToDTO(phongBan.get())
        );
    }
}
