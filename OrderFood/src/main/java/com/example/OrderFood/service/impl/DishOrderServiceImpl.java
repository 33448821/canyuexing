package com.example.OrderFood.service.impl;

import cn.hutool.core.util.StrUtil;
import com.example.OrderFood.dto.OrderDTO;
import com.example.OrderFood.entity.DishOrder;
import com.example.OrderFood.mapper.DishOrderMapper;
import com.example.OrderFood.service.IDishOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.OrderFood.vo.OrderVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xingchen
 * @since 2024-03-05
 */
@Service
public class DishOrderServiceImpl extends ServiceImpl<DishOrderMapper, DishOrder> implements IDishOrderService {

    @Override
    public List<OrderVO> getOrderDTOListByUsername(String username) {
        if (StrUtil.isBlank(username)){
            return null;
        }
        List<OrderDTO> orderDTOS = this.baseMapper.selectAllOrderDetailsByUsername(username);
        if (orderDTOS == null){
            return null;
        }

        HashMap<String, List<OrderDTO>> orderDTOHashMap = new HashMap<>();
        for (OrderDTO orderDTO : orderDTOS) {
            if (orderDTOHashMap.containsKey(orderDTO.getOrderID())){
                orderDTOHashMap.get(orderDTO.getOrderID()).add(orderDTO);
            }else{
                ArrayList<OrderDTO> list = new ArrayList<>();
                list.add(orderDTO);
                orderDTOHashMap.put(orderDTO.getOrderID(),list);
            }

        }

        ArrayList<OrderVO> orderVOS = new ArrayList<>();
        for (Map.Entry<String,List<OrderDTO>> entry:orderDTOHashMap.entrySet()){
            String orderID = entry.getKey();
            List<OrderDTO> order = entry.getValue();

            OrderVO orderVO = new OrderVO(orderID,order);

            orderVOS.add(orderVO);
        }


        Logger logger = LoggerFactory.getLogger(DishOrderServiceImpl.class);
        logger.info(orderVOS.toString());

        return orderVOS;
    }

    @Override
    public List<DishOrder> getOrderByUsername(String username) {
        return this.baseMapper.selectByUsername(username);
    }

}
