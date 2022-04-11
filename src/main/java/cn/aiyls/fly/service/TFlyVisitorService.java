package cn.aiyls.fly.service;

import cn.aiyls.fly.entity.TFlyVisitor;
import cn.aiyls.fly.enums.ReturnCodes;
import cn.aiyls.fly.mapper.TFlyVisitorMapper;
import cn.aiyls.fly.utils.JwtUtils;
import cn.aiyls.fly.utils.Result;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TFlyVisitorService {

    private final TFlyVisitorMapper visitorMapper;

    @Autowired
    public TFlyVisitorService(TFlyVisitorMapper visitorMapper) {
        this.visitorMapper = visitorMapper;
    }

    @Resource
    HttpServletRequest request;
    /**
     * 新增
     */
    public Object add(JSONObject parmas) {
        TFlyVisitor visitor = parmas.toJavaObject(parmas, TFlyVisitor.class);
        visitor.setCreateTime(LocalDateTime.now());
        visitorMapper.insert(visitor);
        return new Result<Object>(ReturnCodes.success);
    }

    /**
     * 删除
     */
    public Object delete(String visitorId) {
        TFlyVisitor visitor = visitorMapper.selectById(visitorId);
        int index = visitorMapper.updateById(visitor);
        if (index != 1) {
            return new Result<Object>(ReturnCodes.failed, "删除失败");
        }
        return new Result<Object>(ReturnCodes.success, "删除成功");
    }

    /**
     * 更新
     */
    public Object update(JSONObject parmas) {
        TFlyVisitor visitor = parmas.toJavaObject(parmas, TFlyVisitor.class);
        int index = visitorMapper.updateById(visitor);
        if (index != 1) {
            return new Result<Object>(ReturnCodes.failed, "更新失败");
        }
        return new Result<Object>(ReturnCodes.success, "更新成功");
    }

    /**
     * 查询所有
     */
    public Object selectAll(JSONObject params) {
        IPage<TFlyVisitor> page = new Page<>(params.getInteger("pageNum"), params.getInteger("pageSize"));
        QueryWrapper<TFlyVisitor> queryWrapper = new QueryWrapper<>();
        Long currentUserId = JwtUtils.getUserId(request);
        queryWrapper.lambda().eq(TFlyVisitor::getUserId, currentUserId);
        queryWrapper.lambda().eq(TFlyVisitor::getStatus, 1);
        IPage<TFlyVisitor> pageLst = visitorMapper.selectPage(page, queryWrapper);
        return new Result<Object>(ReturnCodes.success, pageLst);
    }

    /**
     * 根据ID查询详情
     */
    public Object selectById(String visitorId) {
        QueryWrapper<TFlyVisitor> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TFlyVisitor::getId, visitorId);
        TFlyVisitor visitor = visitorMapper.selectOne(queryWrapper);
        if (visitor == null) {
            return new Result<Object>(ReturnCodes.failed, "没有数据");
        }
        return new Result<Object>(ReturnCodes.success, visitor);
    }
}
