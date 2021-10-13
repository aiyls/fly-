package cn.aiyls.fly.service;

import cn.aiyls.fly.entity.TFlyCompany;
import cn.aiyls.fly.entity.User;
import cn.aiyls.fly.enums.ReturnCodes;
import cn.aiyls.fly.mapper.TFlyCompanyMapper;
import cn.aiyls.fly.mapper.UserMapper;
import cn.aiyls.fly.utils.JwtUtils;
import cn.aiyls.fly.utils.Result;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
@Transactional
public class TFlyCompanyService {

    private final TFlyCompanyMapper companyMapper;

    private final UserMapper userMapper;

    @Resource
    HttpServletRequest request;

    @Autowired
    public TFlyCompanyService(TFlyCompanyMapper companyMapper, UserMapper userMapper) {
        this.companyMapper = companyMapper;
        this.userMapper = userMapper;
    }

    /**
     * 新增
     */
    public Object add(JSONObject parmas) {
        TFlyCompany company = parmas.toJavaObject(parmas, TFlyCompany.class);
        /** 根据营业执照编号查询是否有已经添加企业 */
        TFlyCompany selectCompanyInfo = companyMapper.selectOne(new QueryWrapper<TFlyCompany>().lambda().eq(TFlyCompany::getCompNumber, company.getCompNumber()));
        if (!ObjectUtils.isEmpty(selectCompanyInfo)) {
            return new Result<Object>(ReturnCodes.existCompanyInfo);
        }
        company.setCreateTime(LocalDateTime.now());
        company.setUpdateTime(LocalDateTime.now());
        companyMapper.insert(company);
        updateUserInfo(2);
        return new Result<Object>(ReturnCodes.success);
    }

    /**
     * 更新个人信息
     */
    public void updateUserInfo(Integer corpAuth) {
        Long userId = JwtUtils.getUserId(request);
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId, userId));
        user.setCorpAuth(corpAuth);
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
    }

    /**
     * 删除
     */
    public Object delete(String visitorId) {
        TFlyCompany company = companyMapper.selectById(visitorId);
        company.setUpdateTime(LocalDateTime.now());
        company.setStatus(3);
        updateUserInfo(0);
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
            return new Result<Object>(ReturnCodes.failed, "该公司不存在");
        }
        updateUserInfo(parmas.getInteger("status"));
        return new Result<Object>(ReturnCodes.success, "更新成功");
    }

    /**
     * 根据添加信息查询企业信息
     */
    public Object selectByUserId() {
        Long userId = JwtUtils.getUserId(request);
        TFlyCompany company = companyMapper.selectOne(new QueryWrapper<TFlyCompany>().lambda().eq(TFlyCompany::getUserId, userId));
        return new Result<Object>(ReturnCodes.success, company);
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
