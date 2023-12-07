package com.example.bejava_cmsbatdongsan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "thongbao_tbl")
public class ThongBao extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "nguoitaothongbaoid", insertable = false, updatable = false)
    @JsonBackReference
    private NguoiDung nguoiTaoThongBao;
    @Column(name = "nguoitaothongbaoid")
    private int nguoiTaoThongBaoId;
    @Column(name = "thoigiantao")
    private LocalDate thoiGianTao;
    @Column(name = "noidung")
    private String noiDung;
    @Column(name = "tieude")
    private String tieuDe;
    @ManyToOne
    @JoinColumn(name = "loaithongbaoid", insertable = false, updatable = false)
    @JsonBackReference
    private LoaiThongBao loaiThongBao;
    @Column(name = "loaithongbaoid")
    private int loaiThongBaoId;

    public ThongBao(NguoiDung nguoiTaoThongBao, int nguoiTaoThongBaoId, LocalDate thoiGianTao, String noiDung, String tieuDe, LoaiThongBao loaiThongBao, int loaiThongBaoId) {
        this.nguoiTaoThongBao = nguoiTaoThongBao;
        this.nguoiTaoThongBaoId = nguoiTaoThongBaoId;
        this.thoiGianTao = thoiGianTao;
        this.noiDung = noiDung;
        this.tieuDe = tieuDe;
        this.loaiThongBao = loaiThongBao;
        this.loaiThongBaoId = loaiThongBaoId;
    }
    public  ThongBao(){}
}
