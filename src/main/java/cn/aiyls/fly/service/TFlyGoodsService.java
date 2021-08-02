package cn.aiyls.fly.service;

import cn.aiyls.fly.entity.TFlyDynamic;
import cn.aiyls.fly.entity.TFlyGoods;
import cn.aiyls.fly.enums.ReturnCodes;
import cn.aiyls.fly.mapper.TFlyGoodsMapper;
import cn.aiyls.fly.utils.Result;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.time.LocalDateTime;

@Service
@Transactional
public class TFlyGoodsService {
    private final TFlyGoodsMapper goodsMapper;

    @Autowired
    public TFlyGoodsService(TFlyGoodsMapper _goodsMaper) {
        this.goodsMapper = _goodsMaper;
    }


    /**
     * 新增商品
     */
    public Object add(JSONObject params) {
        TFlyGoods goods = params.toJavaObject(params, TFlyGoods.class);
        goods.setCreateTime(LocalDateTime.now());
        goods.setUpdateTime(LocalDateTime.now());
        goods.setType(1);
        goodsMapper.insert(goods);
        return new Result<Object>(ReturnCodes.success);
    }

    /**
     * 删除商品
     */
    public Object delete(String[] goodsIds) throws Exception {
        for (int i = 0; i < goodsIds.length; i++) {
            TFlyGoods goods = new TFlyGoods();
            goods.setId(Long.parseLong(goodsIds[i]));
            goods.setStatus(2);
            goodsMapper.updateById(goods);
        }
        return new Result<Object>(ReturnCodes.success);
    }

    /**
     * 更新商品
     */
    public Object update(JSONObject params) {
        TFlyGoods goods = params.toJavaObject(params, TFlyGoods.class);
        goods.setUpdateTime(LocalDateTime.now());
        int index = goodsMapper.updateById(goods);
        if (index != 1) {
            return new Result<Object>(ReturnCodes.failed);
        }
        return new Result<Object>(ReturnCodes.success);
    }

    /**
     * 查询全部商品
     */
    public Object selectAll(JSONObject params) {
        IPage<TFlyGoods> page = new Page<>(params.getInteger("pageNum"), params.getInteger("pageSize"));
        QueryWrapper<TFlyGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TFlyGoods::getUserId, params.getInteger("userId"));
        queryWrapper.lambda().eq(TFlyGoods::getStatus, 1);
        IPage<TFlyGoods> pageLst = goodsMapper.selectPage(page, queryWrapper);
        return new Result<Object>(ReturnCodes.success, pageLst);
    }

    /**
     * 根据ID查询商品详情
     */
    public Object selectById(String goodsId) {
        QueryWrapper<TFlyGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(TFlyGoods::getStatus, 1);
        queryWrapper.lambda().eq(TFlyGoods::getId, goodsId);
        TFlyGoods goods = goodsMapper.selectOne(queryWrapper);
        if (goods == null) {
            return new Result<Object>(ReturnCodes.failed, "没有数据");
        }
        return new Result<Object>(ReturnCodes.success, goods);
    }

}
