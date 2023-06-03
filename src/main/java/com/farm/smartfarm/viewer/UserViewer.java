package com.farm.smartfarm.viewer;

import com.farm.smartfarm.controller.UserController;
import com.farm.smartfarm.model.FarmUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/*
* user controller
* user check, user register, search user's id and pw
*/

@CrossOrigin("http://127.0.0.1:3000")
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Slf4j
public class UserViewer {
    private final UserController userController;
    private final String FALSE = "false";

    private final PwEncryption pwEncryption;
    // 이름과 핸드폰 번호 받아오기
    @PostMapping("/get-id")
    public String getData(String name, String phoneNum) {
        log.info(name + "'s get_id");
//        휴대폰 인증 결과 가져오기
        if(name == null || phoneNum == null) {
            log.warn(name + " or phone false");
            return FALSE;
        }
        String res = userController.searchId(name, phoneNum);

        return res;
    }

    @PostMapping("/register")
    public boolean createUser(@RequestBody FarmUser farmUser) {
        log.info("register - " + farmUser.toString());
//        if(farmUser.getId()) return false;
        farmUser.setPw(pwEncryption.encodeBCryptPw(farmUser.getPw()));
        boolean res = userController.createUser(farmUser);
        if (res) return true;
        return false;
    }

    @PostMapping("/auth/login")
    public boolean checkLogin(@RequestBody HashMap<String, String> user) {
        log.info("try login-"+user.get("id") + "\t" + user.get("pw"));
//        String encryptPw = passwordEncoder.encode(pw);
//        return userController.checkUser(id, encryptPw);
        return userController.checkUser(user.get("id"), user.get("pw"));
    }

    @PostMapping("/auth/set-password")
    public String setPassword(@RequestBody HashMap<String, String> user) {
        log.info("try login-"+user.get("id") + "\t" + user.get("pw"));
        // modify password
        String res = userController.setPassword(user.get("id"), user.get("pw"));
        return res;
    }

    @GetMapping("/check-dup")
    public boolean checkDuplicate(String id) {
        return userController.duplicatedId(id);
    }

    @GetMapping("/get-info")
    public FarmUser getUserInfo(String id) {
//        get User Info
        FarmUser res = userController.getUserInfo(id);
        return res;
    }
    @GetMapping("/test")
    public String test() {
        return "test check";
    }
}
