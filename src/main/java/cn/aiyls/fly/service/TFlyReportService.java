package cn.aiyls.fly.service;

import cn.aiyls.fly.entity.TFlyComment;
import cn.aiyls.fly.entity.TFlyReport;
import cn.aiyls.fly.enums.ReturnCodes;
import cn.aiyls.fly.mapper.TFlyReportMapper;
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
public class TFlyReportService {

    private final TFlyReportMapper reportMapper;

    @Autowired
    public TFlyReportService(TFlyReportMapper reportMapper) {
        this.reportMapper = reportMapper;
    }

    /**
     * 新增
     */
    public Object add(JSONObject parmas) {
        TFlyReport report = parmas.toJavaObject(parmas, TFlyReport.class);
        report.setCreateTime(LocalDateTime.now());
        report.setUpdateTime(LocalDateTime.now());
        reportMapper.insert(report);
        return new Result<Object>(ReturnCodes.success);
    }

    /**
     * 更新
     */
    public Object update(JSONObject parmas) {
        TFlyReport report = parmas.toJavaObject(parmas, TFlyReport.class);
        report.setUpdateTime(LocalDateTime.now());
        int index = reportMapper.updateById(report);
        if (index != 1) {
            return new Result<Object>(ReturnCodes.failed, "更新失败");
        }
        return new Result<Object>(ReturnCodes.success, "更新成功");
    }

    /**
     * 查询所有
     */
    public Object selectAll() {
        List<TFlyReport> reportList = reportMapper.selectList(null);
        return new Result<Object>(ReturnCodes.success, reportList);
    }

    /**
     * 根据ID查询详情
     */
    public Object selectById(String visitorId) {
        QueryWrapper<TFlyReport> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TFlyReport::getId, visitorId);
        TFlyReport report = reportMapper.selectOne(queryWrapper);
        if (report == null) {
            return new Result<Object>(ReturnCodes.failed, "没有数据");
        }
        return new Result<Object>(ReturnCodes.success, report);
    }
}
