package com.example.OrderFood.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author xingchen
 * @since 2024-03-05
 */
@TableName("dish_order")
public class DishOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderId;

    private Integer userId;

    private LocalDateTime orderTime;

    private BigDecimal totalPrice;

    private Byte status;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DishOrder{" +
            "orderId = " + orderId +
            ", userId = " + userId +
            ", orderTime = " + orderTime +
            ", totalPrice = " + totalPrice +
            ", status = " + status +
        "}";
    }
}
