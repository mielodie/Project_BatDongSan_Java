package com.example.bejava_cmsbatdongsan.handle;

import org.springframework.stereotype.Component;

@Component
public class ChuanHoaChuoi {
    public String chuanHoaChuoi(String name){
        name = name.trim().toLowerCase();
        while (name.contains("  ")){
            name = name.replace("  ", " ");
        }
        return name;
    }
}
