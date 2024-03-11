package com.example.OrderFood.mapper;

import com.example.OrderFood.vo.AdminDishVO;
import com.example.OrderFood.dto.OrderDTO;
import com.example.OrderFood.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xingchen
 * @since 2024-03-10
 */
public interface AdminMapper extends BaseMapper<Admin> {
    List<OrderDTO> selectAllOrderDTOs();

    List<AdminDishVO> selectAllOrdersToAdmin();
}
