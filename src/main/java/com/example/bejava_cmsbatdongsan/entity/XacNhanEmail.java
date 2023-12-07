package com.example.bejava_cmsbatdongsan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Builder
@Table(name = "xacnhanemail_tbl")
public class XacNhanEmail extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "nguoidungid", insertable = false, updatable = false)
    @JsonBackReference
    private NguoiDung nguoiDung;
    @Column(name = "nguoidungid")
    private int nguoiDungId;
    @Column(name = "thoidiemxacnhan")
    private LocalDate thoiDiemXacNhan;
    @Column(name = "thoigianhethan")
    private LocalDate thoiGianHetHan;
    @Column(name = "maxacnhan")
    private String code;
    @Column(name = "daxacnhan")
    private boolean daXacNhan;
    public XacNhanEmail(){}

    public XacNhanEmail(NguoiDung nguoiDung, int nguoiDungId, LocalDate thoiDiemXacNhan, LocalDate thoiGianHetHan, String code, boolean daXacNhan) {
        this.nguoiDung = nguoiDung;
        this.nguoiDungId = nguoiDungId;
        this.thoiDiemXacNhan = thoiDiemXacNhan;
        this.thoiGianHetHan = thoiGianHetHan;
        this.code = code;
        this.daXacNhan = daXacNhan;
    }
}
