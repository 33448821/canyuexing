package com.example.OrderFood.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.OrderFood.entity.DishType;
import com.example.OrderFood.mapper.DishTypeMapper;
import com.example.OrderFood.service.IDishTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xingchen
 * @since 2024-03-03
 */
@Service
public class DishTypeServiceImpl extends ServiceImpl<DishTypeMapper, DishType> implements IDishTypeService {

    @Override
    public List<DishType> getDishTypeList() {
        return this.baseMapper.selectList(Wrappers.emptyWrapper());
    }
}
