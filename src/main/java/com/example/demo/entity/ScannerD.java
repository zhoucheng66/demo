package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScannerD {
    //站点ID，默认5100001
    private String stationID;
    //设备ID（杰能科世提供）
    private String ID;
    //经度
    private Double lat;
    //维度
    private Double lng;
    //链路状态
    private Integer linkState;
    //设备IP
    private String IP;
    //网络数据传世大小
    private Double dataRate;
    //是否发现目标
    private Integer foundTarget;


}
