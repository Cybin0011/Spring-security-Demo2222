package com.example.springsecuritydemo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chen Yuanbin
 * @description TODO
 * @create 2023-06-28 16:58
 */
@RestController
public class helloController {
    @GetMapping("/hello")
    public  String hello (){
        return "hello";
    }
}
