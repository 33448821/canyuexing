package com.example.OrderFood.sql;

import com.example.OrderFood.mapper.OrderDetailsMapper;
import com.example.OrderFood.vo.OrderDetailVO;
import com.example.OrderFood.vo.OrderDishVO;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Created by 永恒星辰
 * Date 2024/3/10 0:11
 * Description
 */
@SpringBootTest
public class OrderDetailTest {
    Logger logger = LoggerFactory.getLogger(OrderTest.class);
    @Resource
    private OrderDetailsMapper orderDetailsMapper;

    @Test
    public void getOrderDetailByOrderID(){

        List<OrderDetailVO> orderDetailVOS = orderDetailsMapper.selectOrderDetailByOrderId("123456");
        logger.info(orderDetailVOS.toString());
    }
}
