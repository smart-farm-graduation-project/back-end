package com.farm.smartfarm.controller;

import com.farm.smartfarm.function.ConvertStringToDate;
import com.farm.smartfarm.function.Padding;
import com.farm.smartfarm.model.FarmData;
import com.farm.smartfarm.model.Farm;
import com.farm.smartfarm.model.FarmDataRepository;
import com.farm.smartfarm.model.FarmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FarmController {
    private final FarmDataRepository farmDataRepository;
    private final FarmRepository farmInfoRepository;

    // insert sensor data
    public boolean insertSensorData(String data) {
        // Data : time-sensorName-sensorData-user-farmId
        String[] tmp = data.split("/");
        int i = 0;
        try{
            FarmData farmDataModel = new FarmData();
            for(String type : tmp) {
                log.info(type);
                switch(i){
                    case 0:
                        farmDataModel.setSensorDate(type);
                        i += 1;
                        continue;
                    case 1:
                        farmDataModel.setSensorName(type);
                        i += 1;
                        continue;
                    case 2:
                        farmDataModel.setSensorData(type);
                        i += 1;
                        continue;
                    case 3:
                        farmDataModel.setSensorUser(type);
                        i += 1;
                        continue;
                    case 4:
                        farmDataModel.setFarmNum(type);
                        i += 1;
                }
            }
            log.info(farmDataModel.toString());
            farmDataRepository.save(farmDataModel);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    // new smartFarm register
    public boolean regiFarm (String user) {
        String date = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME).toString().split("\\.")[0];
        ArrayList<Farm> farm = (ArrayList<Farm>) farmInfoRepository.findAll(Sort.by(Sort.Direction.DESC, "farmNum"));
        if (farm.isEmpty()) {
            Farm first = new Farm();
            first.setUser(user);
            first.setStartDate(date);
            String a = "0".repeat(8);
            first.setFarmNum(a);
            farmInfoRepository.save(first);
            return true;
        }
        String beforeFarmNum = farm.get(0).getFarmNum();
        BigInteger num = new BigInteger(beforeFarmNum);
        num = num.add(BigInteger.ONE);
        String farmNum = new Padding().padLeftZeros(num.toString(), 8);
        Farm newInfo = new Farm();
        newInfo.setStartDate(date);
        newInfo.setUser(user);
        newInfo.setFarmNum(farmNum);
        farmInfoRepository.save(newInfo);
        return true;
    }

    // Farm data search (all data)
    public ArrayList<FarmData> searchFarmData(String user) {
        log.info("searchFarmData+" + user);
        ArrayList<FarmData> datas = (ArrayList<FarmData>) farmDataRepository.findAll();
        if (datas.isEmpty()) {
            log.warn("data isn't exist");
            return datas;
        }

        ArrayList<FarmData> res = new ArrayList<>();
        for (FarmData data : datas) {
            if(data.getSensorUser().equals(user)) {
                res.add(data);
            }
        }
        return res;
    }

    // Farm data search ( by period )
    public ArrayList<FarmData> searchFarmDataPeriod(String startDate, String endDate, String user) {
        log.info("sarchFarmData+" + startDate + "+" + endDate);
        ArrayList<FarmData> datas = searchFarmData(user);
        ArrayList<FarmData> res = new ArrayList<>();
        // Date Format
        // YYYY-MM-DDThh:mm:ss
//        받아올때는 연월일만 받아오기
        LocalDate start = ConvertStringToDate.convertString(startDate);
        LocalDate end = ConvertStringToDate.convertString(endDate);
        for(FarmData data : datas) {
            LocalDate period = ConvertStringToDate.convertString(data.getSensorDate());
            if((period.isAfter(start) && period.isBefore(end))
                || period.isEqual(start) || period.isEqual(end)) {
                res.add(data);
            }
        }
        return res;
    }
}
