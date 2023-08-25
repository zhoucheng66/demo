package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drone {
    //站点ID(默认510001)
    private String StationID;
    //设备ID(杰能科世提供)
    private String scanID;
    //探测类型（默认0）
    private Integer detectType;
    //首次发现无人机时间
    private String intrusionStartTime;
    //无人机批次
    private String batchNum;
    //经度
    private Double longitude;
    //维度
    private Double latitude;
    //高度
    private Double height;
    //当前距离
    private Double distance;
    //频段
    private Double frequency;
    //带宽
    private Double bandwidth;
    //速度
    private Double speed;
    //水平角（扩展）
    private Double horizontalHeadingAngle;
    //垂直角（扩展）
    private Double verticalHeadingAngle;
    //型号
    private String model;
    //0遥控器，1无人机
    private Integer type;
    //持续时间
    private Integer lastingTime;
    //无人机唯一ID（白名单）
    private String droneUuid;

}
