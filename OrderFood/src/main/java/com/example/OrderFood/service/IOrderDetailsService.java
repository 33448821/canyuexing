package com.example.OrderFood.service;

import com.example.OrderFood.entity.OrderDetails;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.OrderFood.vo.OrderDetailVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xingchen
 * @since 2024-03-05
 */
public interface IOrderDetailsService extends IService<OrderDetails> {
    boolean addOrderDetail(String orderID, int dishNo,int quantity);

    List<OrderDetailVO> selectOrderDetailByOrderID(String oderID);
}
