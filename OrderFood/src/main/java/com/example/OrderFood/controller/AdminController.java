package com.example.OrderFood.controller;

import com.example.OrderFood.entity.User;
import com.example.OrderFood.vo.AdminDishVO;
import com.example.OrderFood.dto.OrderDTO;
import com.example.OrderFood.entity.DishType;
import com.example.OrderFood.entity.Result;
import com.example.OrderFood.service.IAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xingchen
 * @since 2024-03-10
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    Logger logger = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/orderList")
    public List<OrderDTO> getOrderList() {
        return adminService.getAllOrderList();
    }

    @PutMapping("/completeOrder/{orderID}")
    public Result<?> completeOrder(@PathVariable String orderID) {
        boolean b = adminService.completeOrder(orderID);
        if (b) {
            return Result.success();
        }
        return Result.failure("订单操作失败");
    }


    @PutMapping("/refundOrder/{orderID}")
    public Result<?> refundOrder(@PathVariable String orderID) {
        logger.info("refund:" + orderID);
        boolean b = adminService.refundOrder(orderID);
        if (b) {
            return Result.success();
        }
        return Result.failure("退款失败");
    }

    @PostMapping("/addType/{typeName}")
    public Result<?> addDishType(@PathVariable String typeName) {
        boolean flag = adminService.addDishType(typeName);
        if (flag) {
            return Result.success();
        } else {
            return Result.failure();
        }
    }

    @GetMapping("/typeList")
    public List<DishType> getAllDishTypes() {
        return adminService.getAllDishTypes();
    }

    @PostMapping("/chgTypeName/{id}/{name}")
    public Result<?> changeTypeName(@PathVariable int id, @PathVariable String name) {
        boolean b = adminService.changeTypeNameByID(id, name);
        return Result.success(b);
    }

    @DeleteMapping("/deleteType/{id}")
    public Result<?> deleteType(@PathVariable int id) {
        return Result.success(adminService.deleteDishType(id));
    }

    @GetMapping("/dishList")
    public Result<List<AdminDishVO>> getAllDishToAdmin() {
        return Result.success(adminService.getAllOrdersToAdmin());
    }

    @PutMapping("/updateDish")
    public Result<?> updateDish(@RequestBody AdminDishVO dish){
        return Result.success(adminService.updateDish(dish));
    }

    @DeleteMapping("/deleteDish/{id}")
    public Result<?> deleteDish(@PathVariable("id") int id){
        return Result.success(adminService.deleteDish(id));
    }

    @PostMapping("/addDish")
    public Result<?> addDish(@RequestBody AdminDishVO dish){
        return Result.success(adminService.addDish(dish));
    }

    @GetMapping("userList")
    public Result<?> getUserList(){
        return Result.success(adminService.getAllUsers());
    }

    @PutMapping("/updateUser")
    public Result<?> updateUser(@RequestBody User user){
        return Result.success(adminService.updateUser(user));
    }

    @DeleteMapping("/deleteUser/{username}")
    public Result<?> deleteUser(@PathVariable("username") String username){
        return Result.success(adminService.deleteUserByUsername(username));
    }

}
