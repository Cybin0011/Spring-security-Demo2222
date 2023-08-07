package com.example.springsecuritydemo;

import cn.hutool.core.bean.BeanUtil;

import cn.hutool.core.bean.copier.CopyOptions;
import com.example.springsecuritydemo.entity.test.A;

import com.example.springsecuritydemo.mapper.MenuMapper;
import com.example.springsecuritydemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest

class SpringSecurityDemoApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    MenuMapper menuMapper;

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


    @Test
    void  testUstils(){
        A a = new A("aaa",0,"aaa");
        System.out.println("初始A");
        A a1 = new A("1111", "123");
        System.out.println("初始A1");
        A a2 = new A("tom",18,"student");
        System.out.println("初始A2");
        BeanUtil.copyProperties(a1,a,true,CopyOptions.create().setIgnoreNullValue(true));
        System.out.println("copy1后"+a);
        BeanUtil.copyProperties(a2,a,true,CopyOptions.create().setIgnoreNullValue(true));
        System.out.println("copy2后"+a);
    }

    @Test
    void  testMenuMapper(){
        System.out.println(menuMapper.selectPermissionByUid("1"));
    }
}
