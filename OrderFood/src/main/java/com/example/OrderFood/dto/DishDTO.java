package com.example.OrderFood.dto;

/**
 * Created by 永恒星辰
 * Date 2024/3/7 21:11
 * Description
 */
public class DishDTO {
    private Integer dishno;
    private int quantity;

    @Override
    public String toString() {
        return "DishDTO{" +
                "dishno=" + dishno +
                ", quantity=" + quantity +
                '}';
    }

    public DishDTO(Integer dishno, int quantity) {
        this.dishno = dishno;
        this.quantity = quantity;
    }

    public Integer getDishno() {
        return dishno;
    }

    public void setDishno(Integer dishno) {
        this.dishno = dishno;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
