package com.example.springsecuritydemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.springsecuritydemo.entity.LoginUser;
import com.example.springsecuritydemo.entity.User;
import com.example.springsecuritydemo.mapper.MenuMapper;
import com.example.springsecuritydemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chen Yuanbin
 * @description TODO
 * @create 2023-07-04 21:45
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        内部用来校验用户信息，并返回被封装过的用户

//        自定义用让输入的用户信息与数据库中的校验
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, username));
        if (user == null) {
            throw  new RuntimeException("用户名或密码错误");
        }
//        TODO 查询对应的权限信息
        List<String> list = menuMapper.selectPermissionByUid(user.getId().toString());
//        返回被封装过的用户
        return new LoginUser(user,list);
    }
}
