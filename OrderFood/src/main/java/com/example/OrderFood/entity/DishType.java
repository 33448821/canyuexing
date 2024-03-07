package com.example.OrderFood.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xingchen
 * @since 2024-03-03
 */
@TableName("dish_type")
public class DishType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "type_no", type = IdType.AUTO)
    private Integer typeno;

    @TableField(value = "type_name")
    private String typename;

    public Integer getTypeno() {
        return typeno;
    }

    public void setTypeno(Integer typeno) {
        this.typeno = typeno;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    @Override
    public String toString() {
        return "DishType{" +
            "typeNo = " + typeno +
            ", typeName = " + typename +
        "}";
    }
}
