package com.example.OrderFood.service.impl;

import com.example.OrderFood.entity.OrderDetails;
import com.example.OrderFood.mapper.OrderDetailsMapper;
import com.example.OrderFood.service.IOrderDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xingchen
 * @since 2024-03-05
 */
@Service
public class OrderDetailsServiceImpl extends ServiceImpl<OrderDetailsMapper, OrderDetails> implements IOrderDetailsService {

    @Override
    public boolean addOrderDetail(String orderID,int dishNo, int quantity) {
        OrderDetails orderDetails = new OrderDetails(orderID, dishNo, quantity);
        return this.baseMapper.insert(orderDetails)>0;
    }
}
