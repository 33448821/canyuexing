package com.example.OrderFood.service.impl;

import com.example.OrderFood.entity.Feedback;
import com.example.OrderFood.mapper.FeedbackMapper;
import com.example.OrderFood.service.IFeedbackService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.OrderFood.vo.FeedbackVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xingchen
 * @since 2024-04-13
 */
@Service
public class FeedbackServiceImpl extends ServiceImpl<FeedbackMapper, Feedback> implements IFeedbackService {
    @Resource
    private FeedbackMapper feedbackMapper;

    @Override
    public int addFeedback(Feedback feedback) {
        return baseMapper.insert(feedback);
    }

    @Override
    public List<FeedbackVO> getFeedbackByDishNo(int dishNo) {
        return feedbackMapper.getFeedbackByDishNo(dishNo);
    }
}
