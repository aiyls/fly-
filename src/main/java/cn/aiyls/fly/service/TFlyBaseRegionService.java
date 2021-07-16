package cn.aiyls.fly.service;

import cn.aiyls.fly.entity.TFlyBaseRegion;
import cn.aiyls.fly.enums.ReturnCodes;
import cn.aiyls.fly.mapper.TFlyBaseRegionMapper;
import cn.aiyls.fly.utils.Result;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: aiyls
 * @CreateTime: 2021/6/6
 * @Desc:
 */
@Service
@Transactional
public class TFlyBaseRegionService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final TFlyBaseRegionMapper baseRegionMapper;

    @Autowired
    public TFlyBaseRegionService(TFlyBaseRegionMapper brMapper) {
        this.baseRegionMapper = brMapper;
    }

    /**
     * 查询所有信息
     */
    public Object selectAll() {
        List<TFlyBaseRegion> baseRegionList = baseRegionMapper.selectList(null);
        return new Result<Object>(ReturnCodes.success, baseRegionList);
    }

    /**
     * 按输入参数模糊查询
     */
    public Object getCityByParam(JSONObject params) {
        List<TFlyBaseRegion> baseRegionList = baseRegionMapper.selectList(new LambdaQueryWrapper<TFlyBaseRegion>().like(TFlyBaseRegion::getRegionName, params.getString("keyword")));
        return new Result<Object>(ReturnCodes.success, baseRegionList);
    }
}
