package com.farm.smartfarm.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class SensorStateControlId {

    @Id
    private String farmNum;
}
