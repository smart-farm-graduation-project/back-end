package com.farm.smartfarm.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class FarmInfoModel {
    private Date startDate;
    private String user;
    private String farmNum;
}
