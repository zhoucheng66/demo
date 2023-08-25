package com.example.demo.scheduled;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component

public class SpringTaskJob {

    @Value("${token}")
    private String token;

    @Scheduled(cron ="0/3 * * * * ?")
    public void task(){


        System.out.println(Thread.currentThread().getName()+"韩波hhh");
    }



    //HmacMD5加密
    public static String HmacMD5Eencrypt(byte[] encryptContent,String token) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] e = token.getBytes(StandardCharsets.UTF_8);
        SecretKeySpec secretKey = new SecretKeySpec(e, "HmacMD5");
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(secretKey);
        byte[] secretbt = mac.doFinal(encryptContent);
        return byte2hex(secretbt);
    }

    //字节数字转16进制
    public static String byte2hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

}


