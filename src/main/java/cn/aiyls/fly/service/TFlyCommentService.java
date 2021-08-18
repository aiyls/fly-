package cn.aiyls.fly.service;

import cn.aiyls.fly.entity.TFlyComment;
import cn.aiyls.fly.enums.ReturnCodes;
import cn.aiyls.fly.mapper.TFlyCommentMapper;
import cn.aiyls.fly.utils.Result;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TFlyCommentService {

    private final TFlyCommentMapper commentMapper;

    @Autowired
    public TFlyCommentService(TFlyCommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    /**
     * 新增
     */
    public Object add(JSONObject parmas) {
        TFlyComment comment = parmas.toJavaObject(parmas, TFlyComment.class);
        comment.setCreateTime(LocalDateTime.now());
        comment.setUpdateTime(LocalDateTime.now());
        commentMapper.insert(comment);
        return new Result<Object>(ReturnCodes.success);
    }

    /**
     * 删除
     */
    public Object delete(String visitorId) {
        TFlyComment comment = commentMapper.selectById(visitorId);
        comment.setUpdateTime(LocalDateTime.now());
        comment.setStatus(3);
        int index = commentMapper.updateById(comment);
        if (index != 1) {
            return new Result<Object>(ReturnCodes.failed, "删除失败");
        }
        return new Result<Object>(ReturnCodes.success, "删除成功");
    }

    /**
     * 更新
     */
    public Object update(JSONObject parmas) {
        TFlyComment comment = parmas.toJavaObject(parmas, TFlyComment.class);
        comment.setUpdateTime(LocalDateTime.now());
        int index = commentMapper.updateById(comment);
        if (index != 1) {
            return new Result<Object>(ReturnCodes.failed, "更新失败");
        }
        return new Result<Object>(ReturnCodes.success, "更新成功");
    }

    /**
     * 查询所有
     */
    public Object selectAll(JSONObject parmas) {
        List<TFlyComment> commentList = commentMapper.selectList(null);
        return new Result<Object>(ReturnCodes.success, commentList);
    }

    /**
     * 根据ID查询详情
     */
    public Object selectById(String visitorId) {
        QueryWrapper<TFlyComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TFlyComment::getStatus, 1);
        queryWrapper.lambda().eq(TFlyComment::getId, visitorId);
        TFlyComment comment = commentMapper.selectOne(queryWrapper);
        if (comment == null) {
            return new Result<Object>(ReturnCodes.failed, "没有数据");
        }
        return new Result<Object>(ReturnCodes.success, comment);
    }
}
