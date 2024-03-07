package com.example.OrderFood.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xingchen
 * @since 2024-03-05
 */
@TableName("order_details")
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "details_id", type = IdType.AUTO)
    private Integer detailsId;

    private String orderId;

    private Integer dishNo;

    private Integer quantity;

    public Integer getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(Integer detailsId) {
        this.detailsId = detailsId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getDishNo() {
        return dishNo;
    }

    public void setDishNo(Integer dishNo) {
        this.dishNo = dishNo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
            "detailsId = " + detailsId +
            ", orderId = " + orderId +
            ", dishNo = " + dishNo +
            ", quantity = " + quantity +
        "}";
    }
}
