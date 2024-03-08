package com.example.OrderFood.controller;

import com.example.OrderFood.dto.DishDTO;
import com.example.OrderFood.dto.OrderDTO;
import com.example.OrderFood.entity.DishOrder;
import com.example.OrderFood.entity.Result;
import com.example.OrderFood.service.IDishOrderService;
import com.example.OrderFood.service.IOrderDetailsService;
import com.example.OrderFood.service.IUserService;
import com.example.OrderFood.vo.OrderDishVO;
import com.example.OrderFood.vo.OrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
    @Autowired
    private IUserService userService;
    @Autowired
    private IOrderDetailsService orderDetailsService;

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
     * 用户下单，新增订单2024年3月8日16:18:11
     *
     * @param order 前端取回的订单
     * @return 下单结果
     */
    @PostMapping("/orderDish")
    public Result<?> orderDish(@RequestBody OrderDishVO order){
        // 判断余额
        boolean deductUserBalanceFlag = userService.deductUserBalance(order.getUsername(), order.getTotalPrice());
        if (!deductUserBalanceFlag){
            return Result.failure("余额不足");
        }
        // 新增订单
        String orderID = orderService.addOrder(order.getUsername(), order.getTotalPrice());
        // 新增订单详情
        List<DishDTO> dishDTOS = order.getOrderTabs();
        for (DishDTO dto : dishDTOS) {
            orderDetailsService.addOrderDetail(orderID,dto.getDishno(),dto.getQuantity());
        }
        return Result.success("下单成功");
    }

}
