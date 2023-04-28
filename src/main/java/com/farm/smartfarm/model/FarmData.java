package com.farm.smartfarm.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class FarmData {
    String sensorName;
    String sensorData;
    Date sensorDate;
    String sensorUser;
}
