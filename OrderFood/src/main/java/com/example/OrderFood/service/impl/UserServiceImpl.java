package com.example.OrderFood.service.impl;

import com.auth0.jwt.interfaces.Claim;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.OrderFood.entity.Result;
import com.example.OrderFood.entity.User;
import com.example.OrderFood.mapper.UserMapper;
import com.example.OrderFood.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.OrderFood.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xingchen
 * @since 2024-02-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public String login(String username,String password) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username).eq(User::getPassword,password);
        User loginUser = this.getOne(wrapper);
        if (loginUser != null){
            Map<String,Object> userMap = new HashMap<>();
            userMap.put("username",loginUser.getUsername());
            return JwtUtils.generateJwtToken(userMap);
        }
        return null;
    }

    @Override
    public boolean validateToken(String jwt) {
        Map<String, Claim> userMap = JwtUtils.parseJwtToken(jwt);
        return userMap.get("username") != null;
    }

    @Override
    public User getUserInfo(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        User user = this.getOne(wrapper);
        user.setPassword("******");
        return user;
    }

    @Override
    public User updateUserInfo(User u) {
        if (u == null){
            return null;
        }

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,u.getUsername());
        User user = this.getOne(wrapper);
        user.setName(u.getName());
        user.setPhone(u.getPhone());
        user.setAddress(u.getAddress());

        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getUsername,user.getUsername());
        this.update(user,updateWrapper);

        user.setPassword("******");

        return user;

    }

}
