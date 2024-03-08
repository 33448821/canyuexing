package com.example.OrderFood.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.interfaces.Claim;
import com.example.OrderFood.exception.NotLoginException;
import com.example.OrderFood.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * Created by 永恒星辰
 * Date 2024/3/1 2:17
 * Description
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Logger logger =LoggerFactory.getLogger(JwtInterceptor.class);
        logger.info("requestURL = " + request.getRequestURI());

        String token = request.getHeader("Authorization");

        // 判断字符串是否为空
        if (!token.startsWith("Bearer")) {
            throw new NotLoginException();
        } else {
            if(StrUtil.isBlank(token.substring(6))){
                throw new NotLoginException();
            }
            token = token.substring(7).trim();
        }
        Map<String, Claim> claimMap = JwtUtils.parseJwtToken(token);
        if (claimMap != null) {
            String username = claimMap.get("username").toString();
            logger.info("校验token合法，用户名:" + username);
            return !StrUtil.isBlank(username);
        }
        throw new NotLoginException();
    }
}