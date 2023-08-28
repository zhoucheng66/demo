package com.example.demo.entity.aeroscopeweb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightRecord {

    //首次收到时间飞机时间
    private String beginAt;
    //最后次收到飞机时间
    private String endAT;


}
