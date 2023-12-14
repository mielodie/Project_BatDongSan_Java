package com.example.bejava_cmsbatdongsan.service.implement;

import com.example.bejava_cmsbatdongsan.handle.helper_handle.ChuanHoaChuoi;
import com.example.bejava_cmsbatdongsan.handle.email_handle.Validate;
import com.example.bejava_cmsbatdongsan.payload.converter.NguoiDungConverter;
import com.example.bejava_cmsbatdongsan.payload.dto.nguoidungdto.NguoiDungDTO;
import com.example.bejava_cmsbatdongsan.payload.request.nguoidung_request.CapNhatThongTinNguoiDungRequest;
import com.example.bejava_cmsbatdongsan.payload.response.ResponseObject;
import com.example.bejava_cmsbatdongsan.repository.NguoiDungRepo;
import com.example.bejava_cmsbatdongsan.repository.PhongBanRepo;
import com.example.bejava_cmsbatdongsan.service.iservice.iNguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.time.LocalDate;

@Service
public class NguoiDungService implements iNguoiDungService {
    @Autowired
    private NguoiDungRepo nguoiDungRepo;
    @Autowired
    private PhongBanRepo phongBanRepo;
    @Autowired
    private NguoiDungConverter nguoiDungConverter;
    @Autowired
    private ResponseObject<NguoiDungDTO> nguoiDungDTOResponseObject;
    @Autowired
    private ChuanHoaChuoi chuanHoa;

    public ResponseObject<NguoiDungDTO> capNhatThongTinNguoiDung(CapNhatThongTinNguoiDungRequest request) {
        var checkNguoiDung = nguoiDungRepo.findById(request.getNguoiDungId());
        if (checkNguoiDung.isEmpty()) {
            return nguoiDungDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "Người dùng này không tồn tại trên hệ thống",
                    null
            );
        }
        checkNguoiDung.get().setNgaySinh(request.getNgaySinh());
        checkNguoiDung.get().setThoiGianCapNhat(LocalDate.now());
        checkNguoiDung.get().setTenNguoiDung(request.getTenNguoiDung());
        if (!Validate.isValidPhoneNumber(request.getSoDienThoai())) {
            return nguoiDungDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "Định dạng số điện thoại không hợp lệ",
                    null
            );
        }
        checkNguoiDung.get().setSoDienThoai(request.getSoDienThoai());
        if (!Validate.isValidEmail(request.getEmail())) {
            return nguoiDungDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "Định dạng email không hợp lệ",
                    null
            );
        }
        checkNguoiDung.get().setEmail(request.getEmail());
        nguoiDungRepo.save(checkNguoiDung.get());
        return nguoiDungDTOResponseObject.responseSuccess(
                "Cập nhật thông tin người dùng thành công",
                nguoiDungConverter.entityToDTO(checkNguoiDung.get())
        );
    }

    public String xoaNguoiDung(int nguoiDungId) {
        var checkNguoiDung = nguoiDungRepo.findById(nguoiDungId);
        if (checkNguoiDung.isEmpty()) {
            return "Người dùng này không tồn tại trên hệ thống";
        }
        checkNguoiDung.get().setActive(false);
        nguoiDungRepo.save(checkNguoiDung.get());
        return "Xóa người dùng thành công";
    }

    public Page<NguoiDungDTO> getAllNguoiDung(int pageNum, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        return nguoiDungRepo
                .findAll(pageRequest)
                .map(x -> nguoiDungConverter.entityToDTO(x));
    }

    public Page<NguoiDungDTO> getByTenTaiKhoan(String username, int pageNum, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
        String tenNguoiDung = "%" + username + "%";
        return nguoiDungRepo
                .findAllByTenTaiKhoanLikeIgnoreCase(tenNguoiDung, pageRequest);
    }

    public ResponseObject<NguoiDungDTO> getBySoDienThoai(String soDienThoai) {
        var nguoiDung = nguoiDungRepo
                .findAll()
                .stream()
                .filter(x -> x.getSoDienThoai().equals(soDienThoai) && x.isActive() == true)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Số điện thoại này không có trên hệ thống"));
        return nguoiDungDTOResponseObject.responseSuccess(
                "Lấy dữ liệu người dùng thành công",
                nguoiDungConverter.entityToDTO(nguoiDung)
        );
    }

    public ResponseObject<NguoiDungDTO> getByEmail(String email) {
        var nguoiDung = nguoiDungRepo
                .findAll()
                .stream()
                .filter(x -> x.getEmail().equals(email) && x.isActive() == true)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Email này không có trên hệ thống"));
        return nguoiDungDTOResponseObject.responseSuccess(
                "Lấy dữ liệu người dùng thành công",
                nguoiDungConverter.entityToDTO(nguoiDung)
        );
    }

    public ResponseObject<NguoiDungDTO> themNguoiDungVaoPhongBan(int nguoiDungId, int phongBanId) {
        var nguoiDung = nguoiDungRepo.findById(nguoiDungId);
        if (nguoiDung.isEmpty()) {
            return nguoiDungDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_NOT_FOUND,
                    "Người dùng này không tồn tại trên hệ thống",
                    null
            );
        }
        var phongBan = phongBanRepo.findById(phongBanId);
        if (phongBan.isEmpty()) {
            return nguoiDungDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_NOT_FOUND,
                    "Phòng ban này không tồn tại trên hệ thống",
                    null
            );
        }
        nguoiDung.get().setPhongBanId(phongBanId);
        nguoiDungRepo.save(nguoiDung.get());
        phongBan.get().setThanhVien(phongBan.get().getThanhVien() + 1);
        phongBanRepo.save(phongBan.get());
        return nguoiDungDTOResponseObject.responseSuccess(
                "Thêm nhân viên vào phòng ban thành công",
                nguoiDungConverter.entityToDTO(nguoiDung.get())
        );
    }
}
