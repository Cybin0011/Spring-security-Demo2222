package com.example.springsecuritydemo.controller;


import org.springframework.security.access.prepost.PreAuthorize;
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
//    @PreAuthorize("hasAnyAuthority('system:test:index')")
    @PreAuthorize("@ex.hasAuthority('system:test:index')")
    public  String hello (){
        return "hello";
    }

    @GetMapping("/hello2")
//    @PreAuthorize("hasAnyAuthority('system:test:index')")
//    @PreAuthorize("@ex.hasAuthority('system:test:index')")
    public  String hello2 (){
        return "hello2";
    }
}
