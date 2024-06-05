package com.example.OrderFood.controller;

import com.example.OrderFood.entity.Feedback;
import com.example.OrderFood.entity.Result;
import com.example.OrderFood.service.IFeedbackService;
import com.example.OrderFood.vo.FeedbackVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xingchen
 * @since 2024-04-13
 */
@RestController
@RequestMapping("/api")
public class FeedbackController {
    @Autowired
    private IFeedbackService feedbackService;


    @PostMapping("/addFeedback")
    public Result<?> addFeedback(@RequestBody Feedback feedback){
        return Result.success(feedbackService.addFeedback(feedback));
    }

    @GetMapping("/getFeedback/{dishNo}")
    public Result<List<FeedbackVO>> getFeedback(@PathVariable("dishNo") int dishNo){
        return Result.success(feedbackService.getFeedbackByDishNo(dishNo));
    }
}
