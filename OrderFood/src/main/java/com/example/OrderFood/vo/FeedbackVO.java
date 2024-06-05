package com.example.OrderFood.vo;

import java.time.LocalDateTime;

/**
 * Created by 永恒星辰
 * Date 2024/4/13 0:45
 * Description
 */
public class FeedbackVO {
    private int id;
    private String name;
    private String comment;
    private LocalDateTime time;

    @Override
    public String toString() {
        return "FeedbackVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", time=" + time +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public FeedbackVO(int id, String name, String comment, LocalDateTime time) {
        this.id = id;
        this.name = name;
        this.comment = comment;
        this.time = time;
    }
}
