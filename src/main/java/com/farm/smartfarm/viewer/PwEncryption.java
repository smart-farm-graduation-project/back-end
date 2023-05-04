package com.farm.smartfarm.viewer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class PwEncryption {
    private final BCryptPasswordEncoder passwordEncoder;
    public String encodeBCryptPw (String pw) {
        String res = passwordEncoder.encode(pw);
        return res;
    }

    public boolean matchBCryptPw (String rawPw, String encodePw) {
        boolean res = passwordEncoder.matches(rawPw, encodePw);
        return res;
    }
}
