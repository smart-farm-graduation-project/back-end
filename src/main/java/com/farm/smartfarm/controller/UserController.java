package com.farm.smartfarm.controller;

import com.farm.smartfarm.model.FarmUser;
import com.farm.smartfarm.model.UserRepository;
import com.farm.smartfarm.viewer.PwEncryption;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserController {
    //ORM Interface
    private final UserRepository userRepository;
    private final PwEncryption pwEncryption;
//    public UserController(UserRepository userRepository, PwEncryption pwEncryption) {
//        this.userRepository = userRepository;
//        this.pwEncryption = pwEncryption;
//    }
    // new user
    public boolean createUser (FarmUser newUser) {
        try{
            if(userRepository.existsById(newUser.getId()))
                throw new Exception();
            userRepository.save(newUser);
            return true;
        } catch(Exception e) {
            log.warn(e.getMessage());
            return false;
        }
    }

    // search id
    public String searchId (String name, String phoneNum) {
        ArrayList<FarmUser> users = (ArrayList<FarmUser>) userRepository.findAll();
        for (FarmUser user : users) {
            if (user.getName().equals(name) && user.getPhoneNum().equals(phoneNum))
                return user.getId();
        }
        return null;
    }

    // login check
    public boolean checkUser (String id, String pw) {
        Optional<FarmUser> user = userRepository.findById(id);
        if (user.isPresent()) {
            log.info(pw + "   " + user.get().getPw());
//            boolean res = (pw.equals(user.get().getPw())) ? true : false;
            boolean res = pwEncryption.matchBCryptPw(pw,user.get().getPw());
            return res;
        }
        log.info("Not exist");
        return false;
    }

    // change pw
    public String setPassword(String id, String pw) {
        Optional<FarmUser> user = userRepository.findById(id);
        if(user.isPresent()) {
            String encryptPw = pwEncryption.encodeBCryptPw(pw);
            FarmUser modifyUser = user.get();
            modifyUser.setPw(encryptPw);
            userRepository.save(modifyUser);
            return "성공적으로 변경하였습니다.";
        }
        return "wrong id";
    }
}
