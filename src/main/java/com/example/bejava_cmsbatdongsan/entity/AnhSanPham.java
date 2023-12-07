package com.example.bejava_cmsbatdongsan.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "anhsanpham_tbl")
@AllArgsConstructor
@NoArgsConstructor
public class AnhSanPham extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "sanphamid", insertable = false, updatable = false)
    @JsonBackReference
    private SanPham sanPham;
    @Column(name = "sanphamid")
    private int sanPhamId;
    @Column(name = "link_image")
    private String linkImg;
}
