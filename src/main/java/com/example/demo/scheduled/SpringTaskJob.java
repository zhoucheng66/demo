package com.example.demo.scheduled;


import cn.hutool.core.lang.Console;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.demo.entity.aeroscopeweb.AeroscopeInfo;
import com.example.demo.entity.aeroscopeweb.FlightRecord;
import com.example.demo.until.Util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@Component

public class SpringTaskJob {

    @Value("${aeroscope.token}")
    private String token;

    @Value("${aeroscope.aeroscopeID}")
    private String aeroscopeID;

    @Value("${aeroscope.statusCode}")
    private String statusCode;

    @Scheduled(cron ="0/3 * * * * ?")
    public void task() throws NoSuchAlgorithmException, InvalidKeyException {

        //查询监听机列表
        HashMap<String, Object> aeroscpeMap = new HashMap<>();
        aeroscpeMap.put("aeroscopeID", aeroscopeID);
        aeroscpeMap.put("statusCode", statusCode);
        aeroscpeMap.put("timestamp", System.currentTimeMillis());

        byte[] encryptContent =(aeroscopeID
                + statusCode
                +System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8);

        String result1 = HttpRequest.post("/webapi/v1/get_aeroscope_info")
                .header("x-aeroscope-username", "用户名")//头信息，多个头信息多次调用此方法即可
                .header("x-aeroscope-signature", Util.hmacMD5Eencrypt(encryptContent,token))
                .form(aeroscpeMap)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body();
        Console.log(result1);

        // Json转对象
        AeroscopeInfo aeroscopeInfo = JSONUtil.toBean(result1, AeroscopeInfo.class);



        //查询实时上报的飞机信息
         Integer page = 20;
         String droneID =" ";
         Long beginTs = Long.valueOf(123459678);
         Long endTs = Long.valueOf(123459678);


        HashMap<String, Object> droneMap = new HashMap<>();
        droneMap.put("aeroscopeID", aeroscopeID);
        droneMap.put("page", page);//默认一页20条
        droneMap.put("timestamp", System.currentTimeMillis());
        droneMap.put("beginTs", "开始时间");
        droneMap.put("endTs", "结束时间");

        byte[] encryptContent1 =(page
                + aeroscopeID
                + droneID
                + beginTs
                + endTs
                + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8);

        String result2 = HttpRequest.post("/webapi/v1/get_flight_record")
                .header("x-aeroscope-username", "用户名")//头信息，多个头信息多次调用此方法即可
                .header("x-aeroscope-signature", Util.hmacMD5Eencrypt(encryptContent1,token))
                .form(droneMap)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body();
        Console.log(result2);




        //查询飞行历史记录
        String orderID ="xxxx";

        HashMap<String, Object> flightMap = new HashMap<>();
        flightMap.put("aeroscopeID", aeroscopeID);
        flightMap.put("page", page);//默认一页20条
        flightMap.put("timestamp", System.currentTimeMillis());
        flightMap.put("beginTs", "开始时间");
        flightMap.put("endTs", "结束时间");
        flightMap.put("droneID", droneID);
        flightMap.put("orderID", orderID);


        byte[] encryptContent2 =(page
                + orderID
                + aeroscopeID
                + droneID
                + beginTs
                + endTs
                + Long.toString(System.currentTimeMillis())).getBytes(StandardCharsets.UTF_8);

        String result3 = HttpRequest.post("/webapi/v1/get_flight_record")
                .header("x-aeroscope-username", "用户名")//头信息，多个头信息多次调用此方法即可
                .header("x-aeroscope-signature", Util.hmacMD5Eencrypt(encryptContent2,token))
                .form(flightMap)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body();
        Console.log(result2);

        FlightRecord flightRecord = JSONUtil.toBean(result3, FlightRecord.class);


    }




}


