package com.example.OrderFood.controller;

import com.example.OrderFood.dto.OrderDTO;
import com.example.OrderFood.entity.DishOrder;
import com.example.OrderFood.entity.Result;
import com.example.OrderFood.service.IDishOrderService;
import com.example.OrderFood.vo.OrderDishVO;
import com.example.OrderFood.vo.OrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xingchen
 * @since 2024-03-05
 */
@RestController
@RequestMapping("/api")
public class DishOrderController {

    Logger logger = LoggerFactory.getLogger(DishOrderController.class);

    @Autowired
    private IDishOrderService orderService;

    // 弃用
    @GetMapping("/userOrderList2/{username}")
    public Result<?> getOrderDTOList(@PathVariable String username){
        List<OrderVO> orderVOList = orderService.getOrderDTOListByUsername(username);
        return Result.success(orderVOList);
    }

    /**
     * 小程序
     *
     * 根据用户名取出左右订单信息，不是详细信息，详细信息在某一订单详情，用户订单列表用
     *
     * @param username 用户名
     * @return 大致订单列表
     */
    @GetMapping("/userOrderList/{username}")
    public Result<?> getOrderList(@PathVariable String username){
        List<DishOrder> orderList = orderService.getOrderByUsername(username);
        return Result.success(orderList);
    }

    /**
     * 小程序
     *
     * 用户下单，获取订单
     *
     * @param order
     * @return
     */
    @PostMapping("/orderDish")
    public Result<?> orderDish(@RequestBody OrderDishVO order){

        return Result.success(order);
    }

}
