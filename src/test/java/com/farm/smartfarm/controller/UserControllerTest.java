package com.farm.smartfarm.controller;

import com.farm.smartfarm.model.FarmUser;
import com.farm.smartfarm.model.UserRepository;
import org.junit.jupiter.api.Test;

class UserControllerTest {
    UserRepository userRepository;

    @Test
    public boolean createUser (FarmUser newUser) {
        try{
            userRepository.save(newUser);
            return true;
        } catch(Exception e) {
//            log.warn(e.getMessage());
            return false;
        }
    }
}