package com.example.OrderFood.controller;

import com.example.OrderFood.entity.Result;
import com.example.OrderFood.service.IDishService;
import com.example.OrderFood.service.impl.DishServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xingchen
 * @since 2024-03-04
 */
@RestController
@RequestMapping("/api")
public class DishController {
    @Autowired
    private IDishService dishService;

    @RequestMapping("/dish")
    public Result<?> getDishList(){
        return Result.success(dishService.getDishList());
    }
}
