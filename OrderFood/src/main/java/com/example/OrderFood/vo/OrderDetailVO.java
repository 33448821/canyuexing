package com.example.OrderFood.vo;

import java.math.BigDecimal;

/**
 * Created by 永恒星辰
 * Date 2024/3/9 23:33
 * Description
 */
public class OrderDetailVO {
    private int dishNo;
    private String dishName;
    private String description;
    private BigDecimal price;
    private int quantity;
    private String img;

    public int getDishNo() {
        return dishNo;
    }

    public void setDishNo(int dishNo) {
        this.dishNo = dishNo;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "OrderDetailVO{" +
                "dishNo=" + dishNo +
                ", dishName='" + dishName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", img='" + img + '\'' +
                '}';
    }

    public OrderDetailVO(int dishNo, String dishName, String description, BigDecimal price, int quantity, String img) {
        this.dishNo = dishNo;
        this.dishName = dishName;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.img = img;
    }
}
