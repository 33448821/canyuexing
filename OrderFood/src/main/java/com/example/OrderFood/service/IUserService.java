package com.example.OrderFood.service;

import com.example.OrderFood.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xingchen
 * @since 2024-02-27
 */
public interface IUserService extends IService<User> {
    //登录，成功返回token
    String login(String username,String password);

    //校验token
    boolean validateToken(String jwt);

    //根据用户名查询用户信息
    User getUserInfo(String username);

    //更新用户
    User updateUserInfo(User u);

}
