package com.farm.smartfarm.controller;

import com.farm.smartfarm.model.FarmUser;
import com.farm.smartfarm.model.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class UserController {
    //ORM Interface
    private final UserRepository userRepository;
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
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
}
