package com.example.bejava_cmsbatdongsan.entity;

import com.example.bejava_cmsbatdongsan.enumeration.RoleEnums;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@Table(name = "role_tbl")
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity{
    @Column(name = "maquyenhan")
    private RoleEnums tenQuyenHan;
    @Column(name = "tenquyenhan")
    private String code;
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<NguoiDung> nguoiDungs;
}
