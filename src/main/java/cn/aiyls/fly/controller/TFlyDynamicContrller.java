package cn.aiyls.fly.controller;

import cn.aiyls.fly.aop.NoEmptyStr;
import cn.aiyls.fly.entity.TFlyDynamic;
import cn.aiyls.fly.enums.ReturnCodes;
import cn.aiyls.fly.service.TFlyDynamicService;
import cn.aiyls.fly.utils.Result;
import cn.aiyls.fly.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/base/open/dynamic")
public class TFlyDynamicContrller {
    private final TFlyDynamicService dynamicService;

    @Autowired
    public TFlyDynamicContrller(TFlyDynamicService dynamicService) {
        this.dynamicService = dynamicService;
    }

    /**
     * 添加动态
     */
    @PostMapping(value = "/create")
    public Object create(@RequestBody JSONObject params) {
        if (params.getInteger("type") == 1 || params.getInteger("type") == 2) {
            if (params.getString("goodsInfo").isEmpty()) {
                return new Result<Object>(ReturnCodes.failed, "商品信息不能为空");
            }
        } else if (params.getString("content").isEmpty()){
            return new Result<Object>(ReturnCodes.failed, "动态内容不能为空");
        }
        return dynamicService.add(params);
    }

    /**
     * 删除
     */
    @PostMapping(value = "/delete")
    public Object delete(@RequestParam("dynamicId") String dynamicId) {
        if (StringUtil.isEmpty(dynamicId)) {
            return new Result<Object>(ReturnCodes.failed, "动态ID不能为空");
        }
        return dynamicService.delete(dynamicId);
    }

    /**
     * 查询列表
     */
    @GetMapping(value = "/dynamicList")
    public Object getDynamicList(@RequestBody JSONObject params) {
        return dynamicService.selectAll(params);
    }

    /**
     * 查询详情
     */
    @GetMapping(value = "/dynamicDetail")
    public Object getDynamicDetailById(@RequestParam("dynamicId") String dynamicId) {
        if (StringUtil.isEmpty(dynamicId)) {
            return new Result<Object>(ReturnCodes.failed, "动态ID不能为空");
        }
        return dynamicService.selectById(dynamicId);
    }

    /**
     * 喜欢
     */
    @GetMapping(value = "/dynamicLike")
    public Object likeDynamicById(@RequestParam("dynamicId") String dynamicId) {
        if (StringUtil.isEmpty(dynamicId)) {
            return new Result<Object>(ReturnCodes.failed, "动态ID不能为空");
        }
        return dynamicService.likeNum(dynamicId);
    }

    /**
     * 评论 回复
     */
    @NoEmptyStr(value = "dynamicId,toUserId,fromUserId,commentText")
    @PostMapping(value = "/dynamicComment")
    public Object dynamicComment(@RequestBody JSONObject params) {
        return dynamicService.commentDynamic(params);
    }

    /**
     * 评论列表
     */
    @GetMapping(value = "/commentList")
    public Object commentList(@RequestBody JSONObject params) {
        return dynamicService.selectCommentList(params);
    }
}
