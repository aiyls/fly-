package cn.aiyls.fly.service;

import cn.aiyls.fly.enums.ReturnCodes;
import cn.aiyls.fly.mapper.TFlyVisitorMapper;
import cn.aiyls.fly.utils.Result;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TFlyVisitorService {

    private final TFlyVisitorMapper visitorMapper;

    @Autowired
    public TFlyVisitorService(TFlyVisitorMapper visitorMapper) {
        this.visitorMapper = visitorMapper;
    }

    /**
     * 新增
     */
    public Object add(JSONObject parmas) {

        return new Result<Object>(ReturnCodes.success);
    }

    /**
     * 删除
     */
    public Object delete(String visitorId) {

        return new Result<Object>(ReturnCodes.success);
    }

    /**
     * 更新
     */
    public Object update(JSONObject parmas) {

        return new Result<Object>(ReturnCodes.success);
    }

    /**
     * 查询所有
     */
    public Object selectAll(JSONObject parmas) {
        return new Result<Object>(ReturnCodes.success);
    }

    /**
     * 根据ID查询详情
     */
    public Object selectById(String visitorId) {

        return new Result<Object>(ReturnCodes.success);
    }
}
