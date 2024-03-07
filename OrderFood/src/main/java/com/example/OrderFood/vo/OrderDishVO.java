package com.example.OrderFood.vo;

import com.example.OrderFood.dto.DishDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 永恒星辰
 * Date 2024/3/7 21:09
 * Description
 */
public class OrderDishVO {
    private List<DishDTO> orderTabs;
    private int totalNum;
    private BigDecimal totalPrice;
    private String username;

    public List<DishDTO> getOrderTabs() {
        return orderTabs;
    }

    public void setOrderTabs(List<DishDTO> orderTabs) {
        this.orderTabs = orderTabs;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "OrderDishVO{" +
                "orderTabs=" + orderTabs +
                ", totalNum=" + totalNum +
                ", totalPrice=" + totalPrice +
                ", username='" + username + '\'' +
                '}';
    }

    public OrderDishVO(List<DishDTO> orderTabs, int totalNum, BigDecimal totalPrice, String username) {
        this.orderTabs = orderTabs;
        this.totalNum = totalNum;
        this.totalPrice = totalPrice;
        this.username = username;
    }
}
