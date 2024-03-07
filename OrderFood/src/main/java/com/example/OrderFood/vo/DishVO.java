package com.example.OrderFood.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * Created by 永恒星辰
 * Date 2024/3/4 19:08
 * Description
 */
public class DishVO {
    private Integer dishno;
    private Integer typeno;
    private String dishname;
    private String description;
    private Double price;
    private String img;
    private int quantity;

    public Integer getDishno() {
        return dishno;
    }

    public void setDishno(Integer dishno) {
        this.dishno = dishno;
    }

    public Integer getTypeno() {
        return typeno;
    }

    public void setTypeno(Integer typeno) {
        this.typeno = typeno;
    }

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public DishVO() {
    }

    public DishVO(Integer dishno, Integer typeno, String dishname, String description, Double price, String img, int quantity) {
        this.dishno = dishno;
        this.typeno = typeno;
        this.dishname = dishname;
        this.description = description;
        this.price = price;
        this.img = img;
        this.quantity = quantity;
    }
}
