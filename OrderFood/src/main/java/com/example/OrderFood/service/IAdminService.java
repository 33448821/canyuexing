package com.example.OrderFood.service;

import com.example.OrderFood.entity.User;
import com.example.OrderFood.vo.AdminDishVO;
import com.example.OrderFood.dto.OrderDTO;
import com.example.OrderFood.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.OrderFood.entity.DishType;
import com.example.OrderFood.vo.FeedbackVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xingchen
 * @since 2024-03-10
 */
public interface IAdminService extends IService<Admin> {

    List<OrderDTO> getAllOrderList();

    boolean completeOrder(String orderID);

    boolean refundOrder(String orderID);

    boolean addDishType(String typeName);

    List<DishType> getAllDishTypes();

    boolean changeTypeNameByID(int id,String name);

    boolean deleteDishType(int id);

    List<AdminDishVO> getAllOrdersToAdmin();

    boolean updateDish(AdminDishVO dish);

    boolean deleteDish(int id);

    boolean addDish(AdminDishVO dish);

    List<User> getAllUsers();

    int updateUser(User user);

    int deleteUserByUsername(String username);

    List<FeedbackVO> getAllFeedbacks();

    int deleteFeedbackById(int id);

}
