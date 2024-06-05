package com.example.OrderFood.mapper;

import com.example.OrderFood.entity.Feedback;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.OrderFood.vo.FeedbackVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xingchen
 * @since 2024-04-13
 */
public interface FeedbackMapper extends BaseMapper<Feedback> {
    List<FeedbackVO> getFeedbackByDishNo(int dishNo);

    List<FeedbackVO> getAllFeedbacks();
}
