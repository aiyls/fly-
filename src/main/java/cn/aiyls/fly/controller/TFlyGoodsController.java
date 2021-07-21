package cn.aiyls.fly.controller;

import cn.aiyls.fly.aop.NoEmptyStr;
import cn.aiyls.fly.entity.TFlyGoods;
import cn.aiyls.fly.enums.ReturnCodes;
import cn.aiyls.fly.service.TFlyGoodsService;
import cn.aiyls.fly.utils.Result;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "/base/auth/goods")
public class TFlyGoodsController {

    private final TFlyGoodsService goodsService;

    @Autowired
    public TFlyGoodsController(TFlyGoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @PostMapping(value = "/createGoods")
    public Object createGoods(@RequestBody JSONObject params) {
        TFlyGoods goods = params.toJavaObject(params, TFlyGoods.class);
        if (goods.getGoodsName() == null || goods.getGoodsName().equals("")) {
            return new Result<Object>(ReturnCodes.failed, "商品名称不能为空");
        }
        if (goods.getGoodsPrice() == null || goods.getGoodsPrice().compareTo(BigDecimal.valueOf(0)) < 1) {
            return new Result<Object>(ReturnCodes.failed, "商品价格不能为空或者0");
        }
        if (goods.getGoodsStock() == null || goods.getGoodsStock() < 0) {
            return new Result<Object>(ReturnCodes.failed, "商品数量不能为空");
        }
        if (goods.getGoodsAddress() == null || goods.getGoodsAddress().equals("")) {
            return new Result<Object>(ReturnCodes.failed, "地址不能为空");
        }
        if (goods.getEnclosure() == null || goods.getEnclosure().equals("")) {
            return new Result<Object>(ReturnCodes.failed, "商品图片不能为空");
        }
        return goodsService.add(params);
    }

    /**
     * 删除
     */



}
