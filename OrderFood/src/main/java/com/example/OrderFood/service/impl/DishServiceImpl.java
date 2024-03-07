package com.example.OrderFood.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.OrderFood.entity.Dish;
import com.example.OrderFood.mapper.DishMapper;
import com.example.OrderFood.service.IDishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.OrderFood.vo.DishVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xingchen
 * @since 2024-03-04
 */
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements IDishService {

    @Override
    public List<DishVO> getDishList() {
        List<Dish> dishList = this.baseMapper.selectList(Wrappers.emptyWrapper());
        ArrayList<DishVO> dishVOS = new ArrayList<>();
        for (Dish dish : dishList) {
            DishVO dishVO = new DishVO();
            dishVO.setQuantity(0);
            BeanUtil.copyProperties(dish,dishVO);
            dishVOS.add(dishVO);
        }

        return dishVOS;
    }
}
