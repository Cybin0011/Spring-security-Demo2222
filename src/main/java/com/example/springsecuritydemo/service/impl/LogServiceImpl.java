package com.example.springsecuritydemo.service.impl;

import com.example.springsecuritydemo.entity.LoginUser;
import com.example.springsecuritydemo.entity.ResponseResult;
import com.example.springsecuritydemo.entity.User;
import com.example.springsecuritydemo.service.LogService;
import com.example.springsecuritydemo.utils.JwtUtil;
import com.example.springsecuritydemo.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chen Yuanbin
 * @description TODO
 * @create 2023-07-04 21:42
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Override
    public ResponseResult login(User userDto) {
//      封装一下user信息，供manager校验时使用
        UsernamePasswordAuthenticationToken AuthenticationToken=
                new UsernamePasswordAuthenticationToken(userDto.getUserName(),userDto.getPassword());
//      校验，并返回再次封装后的user----authenticate
        Authentication authenticate = authenticationManager.authenticate(AuthenticationToken);
//       判断是否校验成功(非空说明校验成功)
        if (authenticate == null) {
            System.out.println("登陆失败");
            throw new RuntimeException("登陆失败");
        }
//        获取uid
//        校验的类---UserDetailsServiceImpl是我们自己实现的
//        而user信息经过它后会被封装成LoginUser，然后在回传的时候会再被封装到authentication里
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String uid = loginUser.getUser().getId().toString();
//        把uid用jwt封装
        String jwt = JwtUtil.createJWT(uid);
//        把完整的用户信息缓存到redis
        redisCache.setCacheObject("login:"+uid,loginUser);
//        返回响应结果
        Map map = new HashMap();
        map.put("token",jwt);
        return new ResponseResult(200,"登陆成功",map);
    }
}
