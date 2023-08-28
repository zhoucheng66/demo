package com.example.demo.entity.aeroscopeweb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DroneRecord2 {
    //绝对高度
    private String absoluteHeight;
    //相对高度
    private String altitude;
    //
    private String latitudeSpeed;
    //
    private String latitudeType;
    //飞行员纬度
    private String appGPSLatitude;
    //飞行员经度
    private String appGPSLongitude;
    //
    private String appGPStime;
    //飞机ID
    private String droneID;
    //飞机类型
    private String droneType;
    //
    private String flightTime;
    //飞行返航点维度
    private String homeLatitude;
    //飞机返航点经度
    private String homeLongitude;
    //飞机维度
    private String latitude;

    //云哨设备ID
    private String aeroscopeID;
    //飞机维度
    private String loc;
    //飞机经度
    private String longtude;
    //
    private String longtudeSpeed;
    //飞机架次编号
    private String orderID;
    //
    private String pitch;
    //飞控对应的飞机类型
    private String productType;

}
