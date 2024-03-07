package com.example.OrderFood.service;

import com.example.OrderFood.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.OrderFood.vo.DishVO;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xingchen
 * @since 2024-03-04
 */
public interface IDishService extends IService<Dish> {

    List<DishVO> getDishList();


}
