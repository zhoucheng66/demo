package com.example.demo.Service.impl;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.demo.Service.ScheduledService;
import com.example.demo.entity.Drone;
import com.example.demo.entity.ScannerD;
import com.example.demo.entity.aeroscopeweb.AeroscopeInfo;
import com.example.demo.entity.aeroscopeweb.DroneRecord;
import com.example.demo.entity.aeroscopeweb.FlightRecord;
import com.example.demo.until.Util;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class ScheduledServiceImpl implements ScheduledService {
    @Value("${aeroscope.token}")
    private String token;

    @Value("${aeroscope.aeroscopeID}")
    private String aeroscopeID;

    @Value("${aeroscope.statusCode}")
    private String statusCode;

    Integer page = 20;
    String droneID =" ";
    Long beginTs = Long.valueOf(123459678);
    Long endTs = Long.valueOf(123459678);
    String orderID ="xxxx";


    //查询监听机列表
    @Override
    public AeroscopeInfo selectByAeroscpe() throws NoSuchAlgorithmException, InvalidKeyException {

        HashMap<String, Object> aeroscpeMap = new HashMap<>();
        aeroscpeMap.put("aeroscopeID", aeroscopeID);
        aeroscpeMap.put("statusCode", statusCode);
        aeroscpeMap.put("timestamp", System.currentTimeMillis());

        byte[] encryptContent =(aeroscopeID
                + statusCode
                +System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8);

        String result1 = HttpRequest.get("/webapi/v1/get_aeroscope_info")
                .header("x-aeroscope-username", "用户名")//头信息，多个头信息多次调用此方法即可
                .header("x-aeroscope-signature", Util.hmacMD5Eencrypt(encryptContent,token))
                .form(aeroscpeMap)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body();
        Console.log(result1);

        // Json转对象
        AeroscopeInfo aeroscopeInfo = JSONUtil.toBean(result1, AeroscopeInfo.class);

        return aeroscopeInfo;
    }



    //查询实时上报的飞机信息
    @Override
    public DroneRecord selectByDroneRecord() throws NoSuchAlgorithmException, InvalidKeyException {

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

        String result2 = HttpRequest.get("/webapi/v1/get_flight_record")
                .header("x-aeroscope-username", "用户名")//头信息，多个头信息多次调用此方法即可
                .header("x-aeroscope-signature", Util.hmacMD5Eencrypt(encryptContent1,token))
                .form(droneMap)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body();
        Console.log(result2);

        DroneRecord droneRecord = JSONUtil.toBean(result2, DroneRecord.class);

        return droneRecord;
    }


    //查询飞行历史记录
    @Override
    public FlightRecord seleteByFlight() throws NoSuchAlgorithmException, InvalidKeyException {
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
                + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8);

        String result3 = HttpRequest.post("/webapi/v1/get_flight_record")
                .header("x-aeroscope-username", "用户名")//头信息，多个头信息多次调用此方法即可
                .header("x-aeroscope-signature", Util.hmacMD5Eencrypt(encryptContent2,token))
                .form(flightMap)//表单内容
                .timeout(20000)//超时，毫秒
                .execute().body();
        Console.log(result3);

        FlightRecord flightRecord = JSONUtil.toBean(result3, FlightRecord.class);

        return flightRecord;
    }

    //设备状态入参
    @Override
    public void saveByScanner(AeroscopeInfo aeroscopeInfo, DroneRecord droneRecord, FlightRecord flightRecord) {

        ScannerD scannerD = new ScannerD();
        scannerD.setStationID("51001");
        scannerD.setID("杰能科世提供");
        scannerD.setLat(aeroscopeInfo.getLatitude());
        scannerD.setLng(aeroscopeInfo.getLongitude());
        scannerD.setLinkState(aeroscopeInfo.getStatusCode());
        scannerD.setIP("设备Ip");
        scannerD.setFoundTarget(ObjectUtil.isEmpty(droneRecord.getLongitude())?0:1);
        scannerD.setDataRate(null);

        HttpUtil.post("192.168.223.5:9803/sys/portable/status/report",JSONUtil.toJsonStr(scannerD));

    }


    //无人机数据上传
    @Override
    public void saveByDrone(AeroscopeInfo aeroscopeInfo, DroneRecord droneRecord, FlightRecord flightRecord) {
        Drone drone = new Drone();
        drone.setStationID("510001");
        drone.setScanID("杰能科世提供");
        //探测类型没有
        drone.setIntrusionStartTime(flightRecord.getBeginAt());
        drone.setBatchNum(droneRecord.getDroneID());
        drone.setLongitude(droneRecord.getLongitude());
        drone.setLatitude(droneRecord.getLatitude());
        drone.setHeight(droneRecord.getAbsoluteHeight());
        //距离，频段，带宽没提供，水平角，垂直角为扩展
        drone.setSpeed(droneRecord.getSpeed());
        drone.setModel(droneRecord.getDroneType());
        drone.setType(1);//无人机
        drone.setLastingTime(flightRecord.getEndAT());
        drone.setDroneUuid(droneRecord.getDroneID());

        HttpUtil.post("http//:192.168.223.5:9802/sys/portable/drone/report",JSONUtil.toJsonStr(drone));


    }

    @Override
    public void saveByControl(DroneRecord droneRecord, FlightRecord flightRecord) {
        Drone drone = new Drone();
        drone.setStationID("510001");
        drone.setScanID("杰能科世提供");
        //探测类型没有
        drone.setIntrusionStartTime(flightRecord.getBeginAt());
        drone.setBatchNum(droneRecord.getDroneID());
        //遥控维度
        drone.setLongitude(droneRecord.getAppGPSLongitude());
        //遙控经度
        drone.setLatitude(droneRecord.getAppGPSLatitude());
        //相对高度
        drone.setHeight(droneRecord.getAltitude());
        //距离，频段，带宽没提供，水平角，垂直角为扩展
        drone.setSpeed(droneRecord.getSpeed());
        drone.setModel(droneRecord.getDroneType());
        drone.setType(0);//遥控
        drone.setLastingTime(flightRecord.getEndAT());
        drone.setDroneUuid(droneRecord.getDroneID());

        HttpUtil.post("http//:192.168.223.5:9802/sys/portable/drone/report",JSONUtil.toJsonStr(drone));

    }
}
