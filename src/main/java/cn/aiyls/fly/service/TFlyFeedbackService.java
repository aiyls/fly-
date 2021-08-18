package cn.aiyls.fly.service;

import cn.aiyls.fly.entity.TFlyComment;
import cn.aiyls.fly.entity.TFlyCompany;
import cn.aiyls.fly.entity.TFlyFeedback;
import cn.aiyls.fly.enums.ReturnCodes;
import cn.aiyls.fly.mapper.TFlyFeedbackMapper;
import cn.aiyls.fly.utils.Result;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class TFlyFeedbackService {

    private final TFlyFeedbackMapper feedbackMapper;

    @Autowired
    public TFlyFeedbackService(TFlyFeedbackMapper feedbackMapper) {
        this.feedbackMapper = feedbackMapper;
    }

    /**
     * 新增
     */
    public Object add(JSONObject parmas) {
        TFlyFeedback feedback = parmas.toJavaObject(parmas, TFlyFeedback.class);
        feedback.setCreateTime(LocalDateTime.now());
        feedback.setUpdateTime(LocalDateTime.now());
        feedbackMapper.insert(feedback);
        return new Result<Object>(ReturnCodes.success);
    }

    /**
     * 更新
     */
    public Object update(JSONObject parmas) {
        TFlyFeedback feedback = parmas.toJavaObject(parmas, TFlyFeedback.class);
        feedback.setUpdateTime(LocalDateTime.now());
        int index = feedbackMapper.updateById(feedback);
        if (index != 1) {
            return new Result<Object>(ReturnCodes.failed, "更新失败");
        }
        return new Result<Object>(ReturnCodes.success, "更新成功");
    }

    /**
     * 根据ID查询详情
     */
    public Object selectById(String visitorId) {
        QueryWrapper<TFlyFeedback> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TFlyFeedback::getId, visitorId);
        TFlyFeedback feedback = feedbackMapper.selectOne(queryWrapper);
        if (feedback == null) {
            return new Result<Object>(ReturnCodes.failed, "没有数据");
        }
        return new Result<Object>(ReturnCodes.success, feedback);
    }
}
