package com.example.bejava_cmsbatdongsan.handle.email_handle;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendMail {
    private String toEmail;
    private String tieuDe;
    private String noiDung;
}
