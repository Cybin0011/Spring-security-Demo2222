package com.example.springsecuritydemo.config;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * @author Chen Yuanbin
 * @description TODO
 * @create 2023-08-02 17:36
 */
@Component
public class MyAuthorizationManager implements AuthorizationManager {
    @Override
    public AuthorizationDecision check(Supplier authentication, Object object) {
        return null;
    }

    @Override
    public void verify(Supplier authentication, Object object) {
        AuthorizationManager.super.verify(authentication, object);
    }
}
