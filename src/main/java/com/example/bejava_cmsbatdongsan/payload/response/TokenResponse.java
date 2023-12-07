package com.example.bejava_cmsbatdongsan.payload.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenResponse {
    private String token;
}
