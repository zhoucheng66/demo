package com.example.demo.Service;

import com.example.demo.entity.aeroscopeweb.AeroscopeInfo;
import com.example.demo.entity.aeroscopeweb.DroneRecord;
import com.example.demo.entity.aeroscopeweb.FlightRecord;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public interface ScheduledService {


    AeroscopeInfo selectByAeroscpe() throws NoSuchAlgorithmException, InvalidKeyException;

    DroneRecord selectByDroneRecord() throws NoSuchAlgorithmException, InvalidKeyException;

    FlightRecord seleteByFlight() throws NoSuchAlgorithmException, InvalidKeyException;

    void saveByScanner(AeroscopeInfo aeroscopeInfo, DroneRecord droneRecord, FlightRecord flightRecord);


    void saveByDrone(AeroscopeInfo aeroscopeInfo, DroneRecord droneRecord, FlightRecord flightRecord);

    void saveByControl(DroneRecord droneRecord, FlightRecord flightRecord);
}
