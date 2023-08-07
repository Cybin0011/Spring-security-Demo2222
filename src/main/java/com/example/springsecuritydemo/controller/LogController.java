package com.example.springsecuritydemo.controller;

import com.example.springsecuritydemo.entity.ResponseResult;
import com.example.springsecuritydemo.entity.User;
import com.example.springsecuritydemo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chen Yuanbin
 * @description TODO
 * @create 2023-07-04 21:26
 */
@RestController
@RequestMapping("/user")
public class LogController {
    @Autowired
    LogService logService;
    @PostMapping ("/login")
    public ResponseResult login(@RequestBody User userDto){
        return logService.login(userDto);
    }
}
