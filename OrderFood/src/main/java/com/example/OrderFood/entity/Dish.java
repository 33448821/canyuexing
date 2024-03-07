package com.example.OrderFood.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xingchen
 * @since 2024-03-04
 */
public class Dish implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dish_no", type = IdType.AUTO)
    private Integer dishno;

    @TableField(value = "type_no")
    private Integer typeno;

    @TableField(value = "dish_name")
    private String dishname;

    private String description;

    private Double price;

    private String img;

    public Dish() {
    }

    public Dish(Integer dishno, Integer typeno, String dishname, String description, Double price, String img) {
        this.dishno = dishno;
        this.typeno = typeno;
        this.dishname = dishname;
        this.description = description;
        this.price = price;
        this.img = img;
    }

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

    @Override
    public String toString() {
        return "Dish{" +
                "dishno=" + dishno +
                ", typeno=" + typeno +
                ", dishname='" + dishname + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", img='" + img + '\'' +
                '}';
    }
}
