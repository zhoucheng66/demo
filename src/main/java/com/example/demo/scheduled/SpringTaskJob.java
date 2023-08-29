package com.example.demo.scheduled;


import cn.hutool.core.lang.Console;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.demo.Service.ScheduledService;
import com.example.demo.entity.ScannerD;
import com.example.demo.entity.aeroscopeweb.AeroscopeInfo;
import com.example.demo.entity.aeroscopeweb.DroneRecord;
import com.example.demo.entity.aeroscopeweb.FlightRecord;
import com.example.demo.until.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

@Component

public class SpringTaskJob {
    @Autowired
    private ScheduledService scheduledService;



    @Scheduled(cron ="0/3 * * * * ?")
    public void task() throws NoSuchAlgorithmException, InvalidKeyException {

        //查询监听机列表
        AeroscopeInfo aeroscopeInfo = scheduledService.selectByAeroscpe();
        //查询实时上报的飞机信息
        DroneRecord droneRecord = scheduledService.selectByDroneRecord();
        //查询飞行历史记录
        FlightRecord flightRecord = scheduledService.seleteByFlight();

        //设备入参
        if (!aeroscopeInfo.getStatusCode().equals("0")) {
            scheduledService.saveByScanner(aeroscopeInfo,droneRecord,flightRecord);
        }

        //无人机入参
        scheduledService.saveByDrone(aeroscopeInfo,droneRecord,flightRecord);

        //遥控入参
        scheduledService.saveByControl(droneRecord,flightRecord);









    }




}


