package com.example.springsecuritydemo.Filter;

import com.example.springsecuritydemo.entity.LoginUser;
import com.example.springsecuritydemo.utils.JwtUtil;
import com.example.springsecuritydemo.utils.RedisCache;
import io.jsonwebtoken.Claims;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author Chen Yuanbin
 * @description TODO
 * @create 2023-07-31 12:00
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    RedisCache redisCache;
    private static String TOKEN = "token";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        1.先验证token
        String token = request.getHeader(TOKEN);
        if (!StringUtils.hasText(token))
        {
//            1.1验证失败，则放行
            filterChain.doFilter(request,response);
            return;
        }
//        2.通过token查询redis里的用户信息
        Claims claims;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            throw new RuntimeException("token非法");
        }
        String uid = claims.getSubject();
        String redisKey = "login:" + uid;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        if(Objects.isNull(loginUser)){
            throw new RuntimeException("用户未登录");
        }
//        3.把用户名信息封装到authentication,存这里就会对用户标记为通过了认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
//        4.把改authentication存入SecurityHolder里
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}
