package com.farm.smartfarm.model;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@Entity
public class SensorStateControlId {

    @Id
    @Column(name = "state_id")
//    @GeneratedValue
    private int id;
    @Column(length = 32)
    private String farmNum;

    @Column
    private int water; // 총량 (L)


    @Column
    private int activateTime;
    // 0.3 ml/ms
    // 300 ml/sec
    // ex) 5sec = 5000ms * 0.3ml = 1500ml*ms
    @Column
    private boolean wind;

    @Column
    private boolean led;

    @Builder
    public SensorStateControlId
            (int id, String farmNum, int water,
             int activateTime, boolean wind, boolean led) {
        this.farmNum = farmNum;
        this.water = water;
        this.activateTime = activateTime;
        this.wind = wind;
        this.led = led;
    }
}
