package com.example.OrderFood.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by 永恒星辰
 * Date 2024/3/11 19:52
 * Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDishVO {
    private Integer dishNo;
    private Integer dishTypeNo;
    private String dishName;
    private String description;
    private Double price;
    private String img;
    private String typeName;
}
