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
@Table(name = "loaiphongban_tbl")
@AllArgsConstructor
@NoArgsConstructor
public class LoaiPhongBan extends BaseEntity{
    @Column(name = "maloaiphongban")
    private String code;
    @Column(name = "tenloaiphongban")
    private String tenLoaiPhongBan;
    @OneToMany(mappedBy = "loaiPhongBan")
    @JsonManagedReference
    private Set<PhongBan> phongBans;
}
