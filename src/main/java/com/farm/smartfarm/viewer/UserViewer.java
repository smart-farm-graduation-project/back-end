package com.farm.smartfarm.viewer;

import com.farm.smartfarm.controller.UserController;
import com.farm.smartfarm.model.FarmUser;
import com.farm.smartfarm.model.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/*
* user controller
* user check, user register, search user's id and pw
*/

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Slf4j
public class UserViewer {
//    private final UserRepository userRepository;
    private final UserController userController;
    private final String FALSE = "false";

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
    public boolean createUser(String name, String id, String pw, String phoneNum, String email) {
//        UserController userController = new UserController(userRepository);
        boolean res = userController.createUser(new FarmUser(name, id, pw, phoneNum, email));
        if (res) return true;
        return false;
    }

    @GetMapping("/test")
    public String test() {
        return "test check";
    }
}
