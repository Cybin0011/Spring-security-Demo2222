package com.example.springsecuritydemo.service;

import com.example.springsecuritydemo.entity.ResponseResult;
import com.example.springsecuritydemo.entity.User;

/**
 * @author Chen Yuanbin
 * @description TODO
 * @create 2023-07-04 21:36
 */
public interface LogService {
    ResponseResult login(User userDto);
}
