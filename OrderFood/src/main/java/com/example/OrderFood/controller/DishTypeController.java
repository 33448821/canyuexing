package com.example.OrderFood.controller;

import com.example.OrderFood.entity.DishType;
import com.example.OrderFood.entity.Result;
import com.example.OrderFood.service.IDishTypeService;
import com.example.OrderFood.service.impl.DishTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xingchen
 * @since 2024-03-03
 */
@RestController
@RequestMapping("/api")
public class DishTypeController {
    @Autowired
    private IDishTypeService dishTypeService;

    @GetMapping("/dishType")
    public Result<List<DishType>> getDishType(){
        List<DishType> dishTypeList = dishTypeService.getDishTypeList();
        if (dishTypeList != null){
            return Result.success(dishTypeList);
        }return Result.failure("没有相关菜品");
    }
}
