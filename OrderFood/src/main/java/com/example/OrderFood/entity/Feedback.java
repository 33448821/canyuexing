package com.example.OrderFood.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author xingchen
 * @since 2024-04-13
 */
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String comments;

    private LocalDateTime createTime;

    private Integer userId;

    private Integer dishNo;

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", comments='" + comments + '\'' +
                ", createTime=" + createTime +
                ", userId=" + userId +
                ", dishNo=" + dishNo +
                '}';
    }

    public Integer getDishNo() {
        return dishNo;
    }

    public void setDishNo(Integer dishNo) {
        this.dishNo = dishNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Feedback(Integer id, String comments, LocalDateTime createTime, Integer userId, Integer dishNo) {
        this.id = id;
        this.comments = comments;
        this.createTime = createTime;
        this.userId = userId;
        this.dishNo = dishNo;
    }
}
