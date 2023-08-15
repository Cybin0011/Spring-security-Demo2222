package com.example.springsecuritydemo.expression;

import com.example.springsecuritydemo.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Chen Yuanbin
 * @description TODO
 * @create 2023-08-09 10:01
 */
@Component("Pex")
public class PersonlizedEx  {
    public boolean  hasPermission(HttpServletRequest request){
        String requestURI = request.getRequestURI();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser)authentication.getPrincipal();
        List<String> permissions = loginUser.getPermissions();
        return permissions.stream().anyMatch(p->requestURI.contains(p));
    }
}
