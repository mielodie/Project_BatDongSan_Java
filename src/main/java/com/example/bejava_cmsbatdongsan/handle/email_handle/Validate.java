package com.example.bejava_cmsbatdongsan.handle.email_handle;

import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    public static boolean isValidEmail(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
            return true;
        } catch (AddressException ex) {
            return false;
        }
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String pattern = "^(84|0[35789])[0-9]{8}$";
        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
