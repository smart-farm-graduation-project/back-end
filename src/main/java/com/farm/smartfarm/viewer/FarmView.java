package com.farm.smartfarm.viewer;

import com.farm.smartfarm.controller.FarmController;
import com.farm.smartfarm.model.FarmData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("farm")
@RequiredArgsConstructor
@Slf4j
public class FarmView {
    private final FarmController farmController;

    @GetMapping("/insert-data")
    public void insertData(String data) {
        if(data.isEmpty()) {
            log.warn("data is empty");
            return;
        }
        log.info("arduinoData - " + data);
        farmController.insertSensorData(data);
    }

    @PostMapping("/register-farm")
    public boolean insertFarm(String id) {
        log.info("regiFarm-"+id);
        if(id.isEmpty()) {
            return false;
        }
        farmController.regiFarm(id);
        return true;
    }

    @GetMapping("/search-data")
    public ArrayList<FarmData> getData(String id) {
        ArrayList<FarmData> data = farmController.searchFarmData(id);
        if (data.isEmpty()) {
            FarmData empty = new FarmData();
            empty.setSensorName("Isn't data");
            data.add(empty);
            return data;
        }
        return data;
    }

    @GetMapping("/search-data-period")
    public ArrayList<FarmData> getPeriodData(String id, String startDate, String endDate) {
        ArrayList<FarmData> data = farmController.searchFarmDataPeriod(startDate, endDate, id);
        if (data.isEmpty()) {
            FarmData empty = new FarmData();
            empty.setSensorName("Isn't data");
            data.add(empty);
            return data;
        }
        return data;
    }
}
