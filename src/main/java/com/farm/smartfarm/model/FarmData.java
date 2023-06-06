package com.farm.smartfarm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
public class FarmData {
    @Id
    @GeneratedValue
    private int id;
//    @Column
//    private String sensorName;
//    @Column
//    private String sensorData;
    @Column
    private String temperature;
    @Column
    private String moisture;
    @Column
    private String co2;
    @Column
    private String groundMoisture;
    @Column
    private String fruitNum;
    @Column
    private String sensorDate;
    @Column
    private String sensorUser;
    @Column
    private String farmNum;

    @Override
    public String toString() {
        String string = temperature + "-" + sensorDate + "-" + co2 + "-" + sensorUser + "-" + sensorUser;
        return string;
    }
}
