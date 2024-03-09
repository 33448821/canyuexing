package com.example.OrderFood.mapper;

import com.example.OrderFood.entity.OrderDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.OrderFood.vo.OrderDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xingchen
 * @since 2024-03-05
 */
public interface OrderDetailsMapper extends BaseMapper<OrderDetails> {
    List<OrderDetailVO> selectOrderDetailByOrderId(@Param("orderID") String orderID);
}
