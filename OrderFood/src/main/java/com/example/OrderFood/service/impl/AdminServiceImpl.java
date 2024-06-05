package com.example.OrderFood.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.OrderFood.entity.*;
import com.example.OrderFood.vo.AdminDishVO;
import com.example.OrderFood.dto.OrderDTO;
import com.example.OrderFood.mapper.*;
import com.example.OrderFood.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.OrderFood.vo.FeedbackVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xingchen
 * @since 2024-03-10
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Resource
    private DishOrderMapper dishOrderMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private DishTypeMapper dishTypeMapper;

    @Resource
    private DishMapper dishMapper;

    @Resource
    private FeedbackMapper feedbackMapper;

    @Override
    public List<OrderDTO> getAllOrderList() {
        List<OrderDTO> orderDTOS = this.baseMapper.selectAllOrderDTOs();
        orderDTOS.sort(Comparator.comparing(OrderDTO::getOrderTime).reversed());
        return orderDTOS;
    }

    @Override
    public boolean completeOrder(String orderID) {
        LambdaUpdateWrapper<DishOrder> w = new LambdaUpdateWrapper<>();
        w.eq(DishOrder::getOrderId, orderID);
        w.set(DishOrder::getStatus,1);
        return dishOrderMapper.update(w) > 0;
    }

    @Override
    public boolean refundOrder(String orderID) {
        LambdaUpdateWrapper<DishOrder> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(DishOrder::getOrderId,orderID);
        DishOrder order = dishOrderMapper.selectOne(wrapper);

        order.setStatus((byte)2);
        Integer userId = order.getUserId();
        User user = userMapper.selectById(userId);
        user.setMoney(user.getMoney().add(order.getTotalPrice()));

        userMapper.updateById(user);

        LambdaUpdateWrapper<DishOrder> dishOrderWrapper = new LambdaUpdateWrapper<>();
        dishOrderWrapper.eq(DishOrder::getOrderId,order.getOrderId());
        int flag = dishOrderMapper.update(order,dishOrderWrapper);

        return flag > 0;
    }

    @Override
    public boolean addDishType(String typeName) {
        DishType dishType = new DishType();
        dishType.setTypename(typeName);
        return dishTypeMapper.insert(dishType)>0;
    }

    @Override
    public List<DishType> getAllDishTypes() {
        return dishTypeMapper.selectList(null);
    }

    @Override
    public boolean changeTypeNameByID(int id,String name) {
        LambdaQueryWrapper<DishType> dishTypeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishTypeLambdaQueryWrapper.eq(DishType::getTypeno,id);
        DishType dishType = dishTypeMapper.selectOne(dishTypeLambdaQueryWrapper);
        dishType.setTypename(name);
        LambdaUpdateWrapper<DishType> dishTypeLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        dishTypeLambdaUpdateWrapper.eq(DishType::getTypeno,dishType.getTypeno());
        return dishTypeMapper.update(dishType,dishTypeLambdaUpdateWrapper)>0;
    }

    @Override
    public boolean deleteDishType(int id) {
        LambdaQueryWrapper<DishType> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DishType::getTypeno,id);
        return dishTypeMapper.delete(wrapper)>0;
    }

    @Override
    public List<AdminDishVO> getAllOrdersToAdmin() {
        return this.baseMapper.selectAllOrdersToAdmin();
    }

    @Override
    public boolean updateDish(AdminDishVO dish) {
        Dish d = new Dish();
        d.setDishno(dish.getDishNo());
        d.setDescription(dish.getDescription());
        d.setDishname(dish.getDishName());
        d.setPrice(dish.getPrice());
        d.setTypeno(dish.getDishTypeNo());

        LambdaUpdateWrapper<Dish> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Dish::getDishno,dish.getDishNo());
        return dishMapper.update(d,wrapper)>0;
    }

    @Override
    public boolean deleteDish(int id) {
        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dish::getDishno,id);
        return dishMapper.delete(wrapper)>0;
    }

    @Override
    public boolean addDish(AdminDishVO dish) {
        Dish d = new Dish(dish.getDishNo(),dish.getDishTypeNo(), dish.getDishName(), dish.getDescription(),dish.getPrice(),String.valueOf(new Date().getTime())+".png");
        return  dishMapper.insert(d)>0;
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public int updateUser(User user) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername());
        return userMapper.update(user,wrapper);
    }

    @Override
    public int deleteUserByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,username);
        return userMapper.delete(wrapper);
    }

    @Override
    public List<FeedbackVO> getAllFeedbacks() {
        return feedbackMapper.getAllFeedbacks();
    }

    @Override
    public int deleteFeedbackById(int id) {
        return feedbackMapper.deleteById(id);
    }

}
