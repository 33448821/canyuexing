package com.example.OrderFood.controller;

import cn.hutool.core.util.StrUtil;
import com.example.OrderFood.entity.Result;
import com.example.OrderFood.entity.User;
import com.example.OrderFood.service.IUserService;
import com.example.OrderFood.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xingchen
 * @since 2024-02-27
 */
@RestController
@RequestMapping("/api")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;

    @GetMapping("/hi")
    public User sayHi(){
        logger.info(userService.getById(1).toString());
        return userService.getById(1);
    }

    /**
     * 登录逻辑
     * @param username 账号
     * @param password 密码
     * @return token
     */
    @GetMapping("/login/{username}/{password}")
    public Result<String> login(@PathVariable String username, @PathVariable String password){
        String token = userService.login(username, password);
        if (!StrUtil.isBlank(token)){
            return Result.success(null,token);
        }
        return Result.failure("登录失败");
    }

    /**
     * 校验token是否过期、是否有误
     * @param token 需要校验的token
     * @return 结果
     */
    @GetMapping("/validateToken/{token}")
    public Result<String> validateToken(@PathVariable String token){
        boolean flag = userService.validateToken(token);
        if (flag){
            return Result.success();
        }else {
            return Result.failure();
        }
    }

    /**
     * 获取用户信息
     * @param username 用户名
     * @return 结果
     */
    @GetMapping("/getUserInfo/{username}")
    public Result<User> getUserInfo(@PathVariable String username){
        return Result.success(userService.getUserInfo(username));
    }

    @PutMapping("/editDeliveryInfo")
    public Result<User> editDeliveryInfo(@RequestBody User user){
        User u = userService.updateUserInfo(user);
        if (u.getUsername() == null){
            return Result.failure("更新地址信息失败");
        }
        return Result.success(u);
    }

    /**
     * 小程序
     *
     * 用户注册
     *
     * @param registerUser 需要注册的用户参数
     * @return 成功返回200，失败返回401
     */
    @PostMapping("/register")
    public Result<?> registerUser(@RequestBody User registerUser){
        User user = userService.insertOneUser(registerUser);
        if (user!=null){
            return Result.success();
        }else{
            return Result.failure();
        }
    }

}
