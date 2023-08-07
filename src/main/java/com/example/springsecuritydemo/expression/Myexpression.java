package com.example.springsecuritydemo.expression;

import com.example.springsecuritydemo.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Chen Yuanbin
 * @description TODO
 * @create 2023-08-02 15:44
 */
@Component("ex")
public class Myexpression {
    public boolean hasAuthority(String authority){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser)authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();
        return permissions.contains(authority);
    }
}
