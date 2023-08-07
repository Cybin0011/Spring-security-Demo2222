package com.example.springsecuritydemo.handler;

import com.alibaba.fastjson.JSON;
import com.example.springsecuritydemo.entity.ResponseResult;
import com.example.springsecuritydemo.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Chen Yuanbin
 * @description TODO
 * @create 2023-08-01 14:41
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(401,"用户认证失败，请重新登录");
        String json = JSON.toJSONString(result);
//          处理异常
        WebUtils.renderString(response,json);
    }
}
