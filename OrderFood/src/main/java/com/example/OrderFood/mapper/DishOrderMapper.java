package com.example.OrderFood.mapper;

import com.example.OrderFood.dto.OrderDTO;
import com.example.OrderFood.entity.DishOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface DishOrderMapper extends BaseMapper<DishOrder> {

    List<OrderDTO> selectAllOrderDetailsByUsername(@Param("username") String username);

    List<DishOrder> selectByUsername(@Param("username") String username);

}
