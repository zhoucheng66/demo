package com.example.demo.entity.aeroscopeweb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AeroscopeInfo {
    //监听机唯一标识ID
   private String aeroscopeID;
   //监听机唯一标识ID
   private String listernerID;
   //备注
   private String remarkInfo;
   //电量
   private String soc;
   //维度
   private Double latitude;
   //经度
   private Double longitude;
   //时间戳
   private Long timestamp;
   //0为旧协议，1为新协议
   private String version;
   //0为离线，1正常，2异常
   private Integer statusCode;
}
