package com.example.OrderFood.controller;

import com.example.OrderFood.entity.Result;
import com.example.OrderFood.service.IOrderDetailsService;
import com.example.OrderFood.vo.OrderDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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
public class OrderDetailsController {

    @Autowired
    private IOrderDetailsService orderDetailsService;

    @GetMapping("/orderDetail/{orderID}")
    public Result<List<OrderDetailVO>> getOrderDetail(@PathVariable String orderID){
        return Result.success(orderDetailsService.selectOrderDetailByOrderID(orderID));
    }

}
