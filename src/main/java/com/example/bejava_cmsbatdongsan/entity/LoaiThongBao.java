package com.example.bejava_cmsbatdongsan.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "loaithongbao_tbl")
@AllArgsConstructor
@NoArgsConstructor
public class LoaiThongBao extends BaseEntity{
    @Column(name = "maloaithongbao")
    private String code;
    @Column(name = "tenloaithongbao")
    private String tenLoaiThongBao;
    @OneToMany(mappedBy = "loaiThongBao")
    @JsonManagedReference
    private Set<ThongBao> thongBaos;
}
