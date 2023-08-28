package com.example.demo.entity.aeroscopeweb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneRecord {
    //相对高度
    private String altitude;
    //飞机维度
    private String latitude;
    //翻滚角
    private String roll;
    //向北速度
    private String longitudeSpeed;
    //用户唯一ID
    private String uuid;
    //飞机的水平速度
    private String speed;
    //监听机唯一标识ID
    private String listennerID;
    //监听机唯一标识ID
    private String aeroscope;
    //垂直速度
    private String altitudeSpeed;
    //绝对高度
    private String absoluteHeight;
    //俯仰角
    private String pitch;
    //飞机类型
    private String productType;
    //飞机经度
    private String longitude;
    //飞行记录唯一标识
    private String orderID;
    //OSD序列号
    private String seqNum;
    //
    private String receivedSeqNum;
    //向东速度
    private String latitudeSpeed;
    //返航点经度
    private String homeLongitude;
    //偏航角
    private String yaw;
    //飞机型号
    private String droneType;
    //飞机序列号SN
    private String droneID;
    //返航点纬度
    private String homeLatitude;
    //数据接受时间戳
    private String receivedTime;
    //飞行记录自增序列号
    private String flightTime;
    //1为绝对高度，0为相对高度
    private String altitudeType;
    //状态属性值
    private String status;





}
