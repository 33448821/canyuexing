package com.example.OrderFood.service;

import com.example.OrderFood.entity.DishType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xingchen
 * @since 2024-03-03
 */
public interface IDishTypeService extends IService<DishType> {
    //获取所有菜品
    List<DishType> getDishTypeList();
}
