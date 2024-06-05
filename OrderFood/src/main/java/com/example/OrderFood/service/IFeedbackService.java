package com.example.OrderFood.service;

import com.example.OrderFood.entity.Feedback;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.OrderFood.vo.FeedbackVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xingchen
 * @since 2024-04-13
 */
public interface IFeedbackService extends IService<Feedback> {

    int addFeedback(Feedback feedback);

    List<FeedbackVO> getFeedbackByDishNo(int dishNo);
}
