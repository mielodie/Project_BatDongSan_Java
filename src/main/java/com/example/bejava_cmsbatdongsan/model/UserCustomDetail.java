package com.example.bejava_cmsbatdongsan.model;

import com.example.bejava_cmsbatdongsan.entity.NguoiDung;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
public class UserCustomDetail implements UserDetails {
    private NguoiDung nguoiDung;
    public UserCustomDetail(NguoiDung nguoiDung){
        this.nguoiDung = nguoiDung;
    }
    /**
     * Returns the authorities granted to the user. Cannot return <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    // Trả về các quyền được cấp cho người dùng
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(nguoiDung.getRole().getTenQuyenHan().name()));
    }

    /**
     * Returns the password used to authenticate the user.
     *
     * @return the password
     */
    // Trả về mật khẩu được sử dụng để xác thực người dùng
    public String getPassword() {
        return nguoiDung.getMatKhau();
    }

    /**
     * Returns the username used to authenticate the user. Cannot return
     * <code>null</code>.
     *
     * @return the username (never <code>null</code>)
     */
    // Trả về tên người dùng được sử dụng để xác thực người dùng
    public String getUsername() {
        return nguoiDung.getTenTaiKhoan();
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    // Cho biết tài khoản của người dùng đã hế hạn hay chưa
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    // Cho biết người dùng bị khóa hay mở khóa
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    // Cho biết thông tin đăng nhập (mật khẩu) của người dùng đã hêt hạn hay chưa
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
     */
    // Cho biết người dùng được bật hay tắt
    public boolean isEnabled() {
        return true;
    }
}
