package com.example.bejava_cmsbatdongsan.service.implement;

import com.example.bejava_cmsbatdongsan.entity.NguoiDung;
import com.example.bejava_cmsbatdongsan.entity.XacNhanEmail;
import com.example.bejava_cmsbatdongsan.handle.email_handle.SendMail;
import com.example.bejava_cmsbatdongsan.handle.email_handle.Validate;
import com.example.bejava_cmsbatdongsan.model.UserCustomDetail;
import com.example.bejava_cmsbatdongsan.payload.converter.NguoiDungConverter;
import com.example.bejava_cmsbatdongsan.payload.dto.nguoidungdto.NguoiDungDTO;
import com.example.bejava_cmsbatdongsan.payload.request.auth_request.*;
import com.example.bejava_cmsbatdongsan.payload.response.ResponseObject;
import com.example.bejava_cmsbatdongsan.payload.response.TokenResponse;
import com.example.bejava_cmsbatdongsan.repository.NguoiDungRepo;
import com.example.bejava_cmsbatdongsan.repository.RoleRepo;
import com.example.bejava_cmsbatdongsan.repository.XacNhanEmailRepo;
import com.example.bejava_cmsbatdongsan.service.iservice.iAuthService;
import com.example.bejava_cmsbatdongsan.utill.JWTAuthenProvider;
import com.example.bejava_cmsbatdongsan.utill.JwtService;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Random;

@Service
public class AuthServiceImpl implements iAuthService {
    @Autowired
    private ResponseObject<NguoiDungDTO> nguoiDungDTOResponseObject;
    @Autowired
    private NguoiDungRepo nguoiDungRepo;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private JWTAuthenProvider jwtAuthenProvider;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private NguoiDungConverter nguoiDungConverter;
    @Autowired
    private XacNhanEmailRepo xacNhanEmailRepo;

    public ResponseObject<NguoiDungDTO> dangKi(DangKiRequest request) {
        if (StringUtils.isBlank(request.getTenTaiKhoan())
                || StringUtils.isBlank(request.getMatKhau())
                || StringUtils.isBlank(request.getEmail())
                || StringUtils.isBlank(request.getSoDienThoai())) {
            return nguoiDungDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "Vui lòng điền đầy đủ thông tin",
                    null
            );
        }
        if (!Validate.isValidEmail(request.getEmail())) {
            return nguoiDungDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "Định dạng email không hợp lệ",
                    null
            );
        }
        if (!Validate.isValidPhoneNumber(request.getSoDienThoai())) {
            return nguoiDungDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "Định dạng số điện thoại không hợp lệ",
                    null
            );
        }
        var checkTenTaiKhoan = nguoiDungRepo.findAll().stream().filter(x -> x.getTenTaiKhoan().equals(request.getTenTaiKhoan())).findFirst().orElse(null);
        var checkEmail = nguoiDungRepo.findAll().stream().filter(x -> x.getEmail().equals(request.getEmail())).findFirst().orElse(null);
        var checkSoDienThoai = nguoiDungRepo.findAll().stream().filter(x -> x.getSoDienThoai().equals(request.getSoDienThoai())).findFirst().orElse(null);
        if (checkTenTaiKhoan != null) {
            return nguoiDungDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "Tên tài khoản này đã tồn tại trên hệ thống",
                    null
            );
        }
        if (checkEmail != null) {
            return nguoiDungDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "Email này đã tồn tại trên hệ thống",
                    null
            );
        }
        if (checkSoDienThoai != null) {
            return nguoiDungDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "Số điện thoại này đã tồn tại trên hệ thống",
                    null
            );
        }
        var role = roleRepo.findById(2);
        NguoiDung nguoiDung = NguoiDung.builder()
                .tenTaiKhoan(request.getTenTaiKhoan())
                .tenNguoiDung(request.getTenNguoiDung())
                .matKhau(passwordEncoder.encode(request.getMatKhau()))
                .ngaySinh(request.getNgaySinh())
                .soDienThoai(request.getSoDienThoai())
                .email(request.getEmail())
                .thoiGianTao(LocalDate.now())
                .thoiGianCapNhat(LocalDate.now())
                .role(role.get())
                .roleId(6)
                .isActive(true)
                .trangThaiNguoiDungId(1)
                .phongBanId(2)
                .build();
        nguoiDungRepo.save(nguoiDung);
        return nguoiDungDTOResponseObject.responseSuccess("Đăng kí thành công", nguoiDungConverter.entityToDTO(nguoiDung));
    }

    public TokenResponse dangNhap(DangNhapRequest request) {
        try {
            var nguoiDung = nguoiDungRepo.findByTenTaiKhoan(request.getTenTaiKhoan());
            if (nguoiDung.isPresent()) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        nguoiDung.get().getTenTaiKhoan(), request.getMatKhau()
                );
                jwtAuthenProvider.authenticate(token);
                UserCustomDetail userCustomDetail = new UserCustomDetail(nguoiDung.get());
                String jwtToken = jwtService.generateToken(userCustomDetail);
                return TokenResponse.builder().token(jwtToken).build();
            } else {
                throw new IllegalArgumentException("Không tìm thấy người dùng");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return TokenResponse.builder().token("ERROR").build();
        }
    }

    public String doiMatKhau(int nguoiDungId, DoiMatKhauRequest request) {
        var nguoiDung = nguoiDungRepo.findById(nguoiDungId);
        if (nguoiDung.isEmpty()) {
            return "Người dùng có id " + nguoiDungId + " không tồn tại trên hệ thống";
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(request.getMatKhauCu(), request.getMatKhauMoi())) {
            return "Mật khẩu cũ không chính xác";
        }
        if (!request.getMatKhauMoi().equals(request.getXacNhanMatKhau())) {
            return "Xác nhận mật khẩu không trùng khớp";
        }
        nguoiDung.get().setMatKhau(bCryptPasswordEncoder.encode(request.getMatKhauMoi()));
        nguoiDungRepo.save(nguoiDung.get());
        return "Đổi mật khẩu thành công";
    }

    public String quenMatKhau(QuenMatKhauRequest request) {
        var nguoiDung = nguoiDungRepo.findByEmail(request.getEmail());
        try{

            if (nguoiDung.isEmpty()) {
                return "Email " + request.getEmail() + " không có trên hệ thống";
            }
            var xacNhanEmail = xacNhanEmailRepo
                    .findAll()
                    .stream()
                    .filter(x -> x.getNguoiDungId() == nguoiDung.get().getId())
                    .findFirst();
            if(xacNhanEmail.isPresent()){
                xacNhanEmailRepo.delete(xacNhanEmail.get());
                xacNhanEmailRepo.save(xacNhanEmail.get());
            }
            XacNhanEmail xacNhan = XacNhanEmail
                    .builder()
                    .nguoiDungId(nguoiDung.get().getId())
                    .daXacNhan(false)
                    .thoiGianHetHan(LocalDateTime.now().plusHours(4).toLocalDate())
                    .code("Than_Bugs_Ngu__" + codeRandom())
                    .build();
            xacNhanEmailRepo.save(xacNhan);
            SendMail sendMail = SendMail
                    .builder()
                    .toEmail(request.getEmail())
                    .tieuDe("Nhận mã xác nhận để tạo mật khẩu mới từ đây: ")
                    .noiDung("Mã kích hoạt của bạn là" + xacNhan.getCode() + " mã này sẽ hết hạn sau 4 tiếng")
                    .build();
            String message = SendToEmail(sendMail);
            return "Gửi mã xác nhận về email thành công, vui lòng kiểm tra email";
        }catch (Exception ex){
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public ResponseObject<NguoiDungDTO> xacNhanTaoMatKhauMoi(XacNhanTaoMatKhauMoiRequest request) {
        var xacNhanEmail = xacNhanEmailRepo.findByCode(request.getMaXacNhan());
        if(xacNhanEmail.isEmpty()){
            return nguoiDungDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "Mã xác nhận không hợp lệ",
                    null
            );
        }
        if(xacNhanEmail.get().getThoiGianHetHan().isBefore(LocalDate.now())){
            return nguoiDungDTOResponseObject.responseError(
                    HttpURLConnection.HTTP_BAD_REQUEST,
                    "Mã xác nhận đã hết hạn",
                    null
            );
        }
        var nguoiDung = nguoiDungRepo.findById(xacNhanEmail.get().getNguoiDungId());
        nguoiDung.get().setMatKhau(passwordEncoder.encode(request.getMatKhauMoi()));
        xacNhanEmailRepo.deleteAll(nguoiDung.get().getXacNhanEmails());
        nguoiDungRepo.save(nguoiDung.get());
        return nguoiDungDTOResponseObject.responseSuccess(
                "Tạo mật khẩu mới thành công",
                nguoiDungConverter.entityToDTO(nguoiDung.get())
        );
    }

    public String SendToEmail(SendMail sendMail) {
        if (!Validate.isValidEmail(sendMail.getToEmail())) {
            return "Định dạng email " + sendMail.getToEmail() + " không đúng định dạng";
        }
        final String userName = "zzznguyenmy@gmail.com";
        final String password = "xjoh gnlf mdkx zbno";
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userName));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(sendMail.getToEmail())
            );
            message.setSubject(sendMail.getTieuDe());
            message.setText(sendMail.getNoiDung());
            message.setContent(sendMail.getNoiDung(), "text/html; charset=utf-8");
            Transport.send(message);
            return "Xác nhận gửi email thành công, lấy mã xác thực";
        } catch (MessagingException e) {
            return "Lỗi khi gửi mail: " + e.getMessage();
        }
    }

    private int codeRandom() {
        Random random = new Random();
        return random.nextInt(900000) + 100000;
    }
}
