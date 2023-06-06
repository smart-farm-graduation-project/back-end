package com.farm.smartfarm.viewer;

import com.farm.smartfarm.controller.FarmController;
import com.farm.smartfarm.controller.StateControl;
import com.farm.smartfarm.model.Farm;
import com.farm.smartfarm.socektConnect.ChatController;
import com.farm.smartfarm.socektConnect.Message;
import com.farm.smartfarm.socektConnect.ChatRoom;
import com.farm.smartfarm.model.FarmData;
import com.farm.smartfarm.socektConnect.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("farm")
@RequiredArgsConstructor
@Slf4j
public class FarmView {
    private final FarmController farmController;
    private final StateControl stateControl;
//    private final ChatService chatService;
//    private final ChatController chatController;
    @GetMapping("/insert-data")
    public void insertData(String data, String fruitNum, String farmNum) {
        if(data.isEmpty()) {
            log.warn("data is empty");
            return;
        }
        log.info("arduinoData - " + data);
        farmController.insertSensorData(data, fruitNum, farmNum);
    }

    @PostMapping("/register-farm")
    public boolean insertFarm(@RequestBody HashMap<String, String> data) {
        log.info("regiFarm-"+data.get("id"));
        if(data.get("id").isEmpty() || data.get("farmNum").isEmpty()) {
            return false;
        }
        farmController.regiFarm(data.get("id"), data.get("farmNum"));
        return true;
    }

    @GetMapping("/search-data")
    public ArrayList<FarmData> getData(String id) {
        ArrayList<FarmData> data = farmController.searchFarmData(id);
        if (data.isEmpty()) {
            FarmData empty = new FarmData();
            empty.setTemperature("Isn't data");
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
            empty.setTemperature("Isn't data");
            data.add(empty);
            return data;
        }
        return data;
    }

    @GetMapping("/get-farm-list")
    public ArrayList<Farm> getFarmList(String id) {
        log.info("get Farm LIst - " + id);
        ArrayList<Farm> farms = farmController.getFarmList(id);
        return farms;
    }
    @PostMapping("/control/water")
    public boolean controlWater(@RequestBody HashMap<String, String> data) {
        String farmNum = data.get("farmNum");
        int waterAmount = Integer.parseInt(data.get("state"));
        log.info("water - " + farmNum + " - " + waterAmount);
        boolean res = stateControl.setWaterAmount(farmNum, waterAmount);
        return res;
    }

    @PostMapping("/control/wind")
    public boolean controlWind(@RequestBody HashMap<String, String> data) {
        String farmNum = data.get("farmNum");
        boolean state = Boolean.parseBoolean(data.get("state"));
        log.info("wind - " + farmNum + " - " + state);
        boolean res = stateControl.setWindLed("wind", farmNum, state);
        return res;
    }

    @PostMapping("/control/led")
    public boolean controlLed(@RequestBody HashMap<String, String> data) {
        String farmNum = data.get("farmNum");
        boolean state = Boolean.parseBoolean(data.get("state"));
        log.info("led - " + farmNum + " - " + state);
        boolean res = stateControl.setWindLed("led", farmNum, state);
        return res;
    }
    // socket function
    /*@PostMapping("/create-room")
    public ChatRoom createRoom(String farmNum) {
        return chatService.createRoom(farmNum);
    }

//    @GetMapping("/find-chat-room")
//    public List<ChatRoom> findAllRoom() {
//        return chatService.findAllRoom();
//    }

    @PostMapping("/control/water")
    public void controlWater(@RequestBody Message message) {
        log.info("enter - " + message.getRoomId());
        chatController.message(message);
//        ChatRoom roomId = chatService.findRoomById(farmNum);
//        chatService.sendMessage();
//        chatService.message(message);
//        String res = "";
//        return res;
    }*/
}
