package com.example.OrderFood.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by 永恒星辰
 * Date 2024/3/10 18:36
 * Description
 */
@Data
@AllArgsConstructor
public class RefundDTO {
    private String username;
    private String orderID;
}
