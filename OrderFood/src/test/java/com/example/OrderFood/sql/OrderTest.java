package com.example.OrderFood.sql;

import com.example.OrderFood.dto.OrderDTO;

import com.example.OrderFood.mapper.DishOrderMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;


import java.util.List;


/**
 * Created by 永恒星辰
 * Date 2024/3/5 14:23
 * Description
 */
@SpringBootTest
public class OrderTest {
    Logger logger = LoggerFactory.getLogger(OrderTest.class);

    @Resource
    private DishOrderMapper dishOrderMapper;

    @Test
    public void selectAllOrderDetails(){
        List<OrderDTO> dishOrders = dishOrderMapper.selectAllOrderDetailsByUsername("13123456789");
        logger.info(dishOrders.toString());
    }
}
