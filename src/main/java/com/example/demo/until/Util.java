package com.example.demo.until;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Util {
    //HmacMD5加密
    public static String hmacMD5Eencrypt(byte[] encryptContent,String token) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] e = token.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKey = new SecretKeySpec(e, "HmacMD5");
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(secretKey);
        byte[] secretbt = mac.doFinal(encryptContent);
        return byte2hex(secretbt);
    }
    //字节转换为十六进制
    public static String byte2hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}

