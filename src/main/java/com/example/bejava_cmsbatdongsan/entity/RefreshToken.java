package com.example.bejava_cmsbatdongsan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "refreshtoken_tbl")
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken extends BaseEntity{
    @Column(name = "token")
    private String token;
    @Column(name = "thoigianhethan")
    private LocalDate thoiGianHetHan;
    @ManyToOne
    @JoinColumn(name = "nguoiDungId", insertable = false, updatable = false)
    @JsonBackReference
    private NguoiDung nguoiDung;
    @Column(name = "nguoiDungId")
    private int nguoiDungId;
}
