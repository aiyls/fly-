package cn.aiyls.fly.service;

import cn.aiyls.fly.entity.TFlyDynamic;
import cn.aiyls.fly.entity.User;
import cn.aiyls.fly.enums.ReturnCodes;
import cn.aiyls.fly.mapper.TFlyDynamicMapper;
import cn.aiyls.fly.utils.JwtUtils;
import cn.aiyls.fly.utils.Result;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
@Transactional
public class TFlyDynamicService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final TFlyDynamicMapper dynamicMapper;

    @Resource
    HttpServletRequest request;

    @Autowired
    public TFlyDynamicService(TFlyDynamicMapper dynamicMapper) {
        this.dynamicMapper = dynamicMapper;
    }

    /**
     * 查询动态
     */
    public Object selectAll(JSONObject params) {
        // 分页
        IPage<TFlyDynamic> page = new Page<>(params.getInteger("pageNum"), params.getInteger("pageSize"));
        // 条件构造器
        QueryWrapper<TFlyDynamic> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TFlyDynamic::getType,params.getInteger("type")).eq(TFlyDynamic::getStatus, 1).orderByAsc(TFlyDynamic::getCreateTime,TFlyDynamic::getUpdateTime,TFlyDynamic::getGrade);
        IPage<TFlyDynamic> pageList = dynamicMapper.selectPage(page, queryWrapper);
        return new Result<Object>(ReturnCodes.success,pageList);
    }

    /**
     * 查询动态详情
     */
    public Object selectById(String dynamicId) {
        this.seeNum(dynamicId);
        TFlyDynamic dynamic = dynamicMapper.selectOne(new QueryWrapper<TFlyDynamic>().lambda().eq(TFlyDynamic::getId, Long.valueOf(dynamicId)).eq(TFlyDynamic::getStatus, 1));
        return new Result<Object>(ReturnCodes.success, dynamic);
    }

    /**
     * 更新动态
     */
    public Object update(JSONObject params) {
        TFlyDynamic dynamic = params.toJavaObject(params, TFlyDynamic.class);
        dynamic.setUpdateTime(LocalDateTime.now());
        int index = dynamicMapper.updateById(dynamic);
        if (index != 1) {
            return new Result<Object>(ReturnCodes.failed, "更新失败");
        }
        return new Result<Object>(ReturnCodes.success, "更新成功");
    }

    /**
     * 喜欢
     */
    public Object likeNum(String id) {
        TFlyDynamic dynamic = dynamicMapper.selectOne(new QueryWrapper<TFlyDynamic>().lambda().eq(TFlyDynamic::getId, Long.valueOf(id)));
        dynamic.setLikeNum(dynamic.getLikeNum() + 1);
        dynamic.setUpdateTime(LocalDateTime.now());
        int index = dynamicMapper.updateById(dynamic);
        if (index != 1) {
            return new Result<Object>(ReturnCodes.failed, "更新失败");
        }
        return new Result<Object>(ReturnCodes.success, "更新成功");
    }

    /**
     * 评论数量
     */
    public Object commentNum(String id) {
        TFlyDynamic dynamic = dynamicMapper.selectOne(new QueryWrapper<TFlyDynamic>().lambda().eq(TFlyDynamic::getId, Long.valueOf(id)));
        dynamic.setCommentNum(dynamic.getCommentNum() + 1);
        dynamic.setUpdateTime(LocalDateTime.now());
        int index = dynamicMapper.updateById(dynamic);
        if (index != 1) {
            return new Result<Object>(ReturnCodes.failed, "更新失败");
        }
        return new Result<Object>(ReturnCodes.success, "更新成功");
    }

    /**
     * 查看人数
     */
    public void seeNum(String id) {
        TFlyDynamic dynamic = dynamicMapper.selectOne(new QueryWrapper<TFlyDynamic>().lambda().eq(TFlyDynamic::getId, Long.valueOf(id)));
        dynamic.setSeeNum(dynamic.getSeeNum() + 1);
        dynamic.setUpdateTime(LocalDateTime.now());
        dynamicMapper.updateById(dynamic);
    }

    /**
     * 添加动态
     */
    public Object add(JSONObject params) {
        User user = JwtUtils.getUser(request);
        TFlyDynamic dynamic = params.toJavaObject(params, TFlyDynamic.class);
        dynamic.setUserId(user.getId());
        dynamic.setUserName(user.getNickname().isEmpty() ? user.getUserName() : user.getNickname());
        dynamic.setCreateTime(LocalDateTime.now());
        dynamic.setUpdateTime(LocalDateTime.now());
        dynamicMapper.insert(dynamic);
        return new Result<Object>(ReturnCodes.success);
    }

    /**
     * 删除动态
     */
    public Object delete(String dynamicId) {
        Long userId = JwtUtils.getUserId(request);
        TFlyDynamic dynamic = dynamicMapper.selectOne(new QueryWrapper<TFlyDynamic>().lambda().eq(TFlyDynamic::getId, Long.valueOf(dynamicId)).eq(TFlyDynamic::getUserId, userId).ne(TFlyDynamic::getStatus, 3));
        if (dynamic == null) {
            return new Result<Object>(ReturnCodes.failed, "数据不存在");
        }
        dynamic.setUpdateTime(LocalDateTime.now());
        dynamic.setStatus(3);
        int index = dynamicMapper.updateById(dynamic);
        if (index != 1) {
            return new Result<Object>(ReturnCodes.failed, "删除失败");
        }
        return new Result<Object>(ReturnCodes.success, "删除成功");
    }
}
