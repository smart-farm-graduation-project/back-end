package com.farm.smartfarm.controller;


import com.farm.smartfarm.model.SensorStateControlId;
import com.farm.smartfarm.model.SensorStateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
@Service
@Slf4j
@RequiredArgsConstructor
public class StateControl {

    private final SensorStateRepository sensorStateRepository;

    public boolean setWaterAmount(String farmNum, int waterAmount) {
        try {
            SensorStateControlId state =
                    sensorStateRepository
                            .findAll(Sort.by(Sort.Direction.DESC, "id"))
                            .get(0);
            int activateTime = (int) Math.round(waterAmount * 1000 / 0.3);
            SensorStateControlId sensor =
                    SensorStateControlId.builder()
                            .id(state.getId() + 1)
                            .farmNum(farmNum)
                            .water(waterAmount)
                            .activateTime(activateTime)
                            .wind(state.isWind())
                            .led(state.isLed())
                            .build();
            sensorStateRepository.save(sensor);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setWindLed(String type, String farmNum, boolean state) {
        try {
            SensorStateControlId curState =
                    sensorStateRepository
                            .findAll(Sort.by(Sort.Direction.DESC, "id"))
                            .get(0);
            SensorStateControlId sensor = null;
            if (type.equals("wind")) {
                sensor = SensorStateControlId.builder()
                                .id(curState.getId() + 1)
                                .farmNum(farmNum)
                                .water(curState.getWater())
                                .activateTime(curState.getActivateTime())
                                .wind(state)
                                .led(curState.isLed())
                                .build();
            } else {
                sensor = SensorStateControlId.builder()
                                .id(curState.getId() + 1)
                                .farmNum(farmNum)
                                .water(curState.getWater())
                                .activateTime(curState.getActivateTime())
                                .wind(curState.isWind())
                                .led(state)
                                .build();
            }
            sensorStateRepository.save(sensor);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
