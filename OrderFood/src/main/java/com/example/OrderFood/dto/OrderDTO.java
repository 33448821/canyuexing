package com.example.OrderFood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by 永恒星辰
 * Date 2024/3/5 14:08
 * Description 菜品、单价、数量、总价、下单时间、状态
 */
@Data
@AllArgsConstructor
public class OrderDTO {
    private String orderID;
    private String img;
    private String dishName;
    private BigDecimal price;
    private int quantity;
    private BigDecimal totalPrice;
    private LocalDateTime orderTime;
    private byte status;
}
