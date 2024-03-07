package com.example.OrderFood.mapper;

import com.example.OrderFood.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xingchen
 * @since 2024-02-27
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where username = #{username}")
    User selectOneUserByUsername(String username);
}
