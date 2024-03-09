package com.example.OrderFood.service.impl;

import com.example.OrderFood.entity.Admin;
import com.example.OrderFood.mapper.AdminMapper;
import com.example.OrderFood.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
