package com.example.OrderFood.vo;

import com.example.OrderFood.dto.OrderDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by 永恒星辰
 * Date 2024/3/4 4:41
 * Description
 */
public class OrderVO {
    private String orderID;
    private List<OrderDTO> orderDTOS;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public List<OrderDTO> getOrderDTOS() {
        return orderDTOS;
    }

    public void setOrderDTOS(List<OrderDTO> orderDTOS) {
        this.orderDTOS = orderDTOS;
    }

    public OrderVO(String orderID, List<OrderDTO> orderDTOS) {
        this.orderID = orderID;
        this.orderDTOS = orderDTOS;
    }
}
