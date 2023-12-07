package com.example.bejava_cmsbatdongsan.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "trangthainguoidung_tbl")
public class TrangThaiNguoiDung extends BaseEntity{
    @Column(name = "matrangthai")
    private String code;
    @Column(name = "tentrangthai")
    private String tenTrangThai;
    @OneToMany(mappedBy = "trangThaiNguoiDung")
    @JsonManagedReference
    private Set<NguoiDung> nguoiDungs;

    public TrangThaiNguoiDung(String code, String tenTrangThai, Set<NguoiDung> nguoiDungs) {
        this.code = code;
        this.tenTrangThai = tenTrangThai;
        this.nguoiDungs = nguoiDungs;
    }
    public TrangThaiNguoiDung(){}
}
