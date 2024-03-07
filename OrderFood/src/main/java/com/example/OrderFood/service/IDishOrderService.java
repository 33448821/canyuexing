package com.example.OrderFood.service;

import com.example.OrderFood.dto.OrderDTO;
import com.example.OrderFood.entity.DishOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.OrderFood.vo.OrderVO;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xingchen
 * @since 2024-03-05
 */
public interface IDishOrderService extends IService<DishOrder> {
    List<OrderVO> getOrderDTOListByUsername(String username);

    List<DishOrder> getOrderByUsername(String username);
}
