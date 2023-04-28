package com.farm.smartfarm.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class FarmDataModel {
    private String sensorName;
    private String sensorData;
    private Date sensorDate;
    private String sensorUser;
}
