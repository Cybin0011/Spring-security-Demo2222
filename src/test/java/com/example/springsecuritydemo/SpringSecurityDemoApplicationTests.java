package com.example.springsecuritydemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest

class SpringSecurityDemoApplicationTests {

    @Test
    void contextLoads() {
    }
    @Test
    void  testEncode(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("1234abcd"));
        System.out.println(bCryptPasswordEncoder.matches("1234abcd","$2a$10$hA0Lqm4mT0P5Mz2Qcxq40u3JdUAgnlW.gtipNzgYRevBQmtXUU.Sq"));
        System.out.println(bCryptPasswordEncoder.matches("1234abcd","$2a$10$IYk2kryGFfpnlAWQaMUf7e62u9ysHp5L.8R97LNUlsTWY2OnDcxii"));

    }

}
