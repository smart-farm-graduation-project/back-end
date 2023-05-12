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
    private int id;
    @Column
    private String sensorName;
    @Column
    private String sensorData;
    @Column
    private String sensorDate;
    @Column
    private String sensorUser;
    @Column
    private String farmNum;

    @Override
    public String toString() {
        String string = sensorData + "-" + sensorDate + "-" + sensorName + "-" + sensorUser + "-" + sensorUser;
        return string;
    }
}
