package cn.aiyls.fly.service;

import cn.aiyls.fly.entity.TFlyCompany;
import cn.aiyls.fly.enums.ReturnCodes;
import cn.aiyls.fly.mapper.TFlyCompanyMapper;
import cn.aiyls.fly.utils.Result;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class TFlyCompanyService {

    private final TFlyCompanyMapper companyMapper;

    @Autowired
    public TFlyCompanyService(TFlyCompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }

    /**
     * 新增
     */
    public Object add(JSONObject parmas) {
        TFlyCompany company = parmas.toJavaObject(parmas, TFlyCompany.class);
        company.setCreateTime(LocalDateTime.now());
        company.setUpdateTime(LocalDateTime.now());
        companyMapper.insert(company);
        return new Result<Object>(ReturnCodes.success);
    }

    /**
     * 删除
     */
    public Object delete(String visitorId) {
        TFlyCompany company = companyMapper.selectById(visitorId);
        company.setUpdateTime(LocalDateTime.now());
        company.setStatus(3);
        int index = companyMapper.updateById(company);
        if (index != 1) {
            return new Result<Object>(ReturnCodes.failed, "删除失败");
        }
        return new Result<Object>(ReturnCodes.success, "删除成功");
    }

    /**
     * 更新
     */
    public Object update(JSONObject parmas) {
        TFlyCompany company = parmas.toJavaObject(parmas, TFlyCompany.class);
        company.setUpdateTime(LocalDateTime.now());
        int index = companyMapper.updateById(company);
        if (index != 1) {
            return new Result<Object>(ReturnCodes.failed, "更新失败");
        }
        return new Result<Object>(ReturnCodes.success, "更新成功");
    }

    /**
     * 根据ID查询详情
     */
    public Object selectById(String visitorId) {
        QueryWrapper<TFlyCompany> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TFlyCompany::getStatus, 1);
        queryWrapper.lambda().eq(TFlyCompany::getId, visitorId);
        TFlyCompany company = companyMapper.selectOne(queryWrapper);
        if (company == null) {
            return new Result<Object>(ReturnCodes.failed, "没有数据");
        }
        return new Result<Object>(ReturnCodes.success, company);
    }
}
