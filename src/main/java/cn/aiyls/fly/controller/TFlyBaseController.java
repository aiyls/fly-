package cn.aiyls.fly.controller;

import cn.aiyls.fly.aop.CrossDomainWhether;
import cn.aiyls.fly.aop.NoEmptyStr;
import cn.aiyls.fly.service.TFlyBaseRegionService;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/base/open/region")
public class TFlyBaseController {
    private final TFlyBaseRegionService baseRegionService;

    @Autowired
    public TFlyBaseController(TFlyBaseRegionService regionService) {
        this.baseRegionService = regionService;
    }

    /**
     * 返回所有地区数据
     * @return
     */
    @GetMapping("/getCityOrderByLetter")
    public Object getCityOrderByLetter() {
        return baseRegionService.selectAll();
    }

    /**
     * 检索地区
     * @param keyword
     * @return
     */
    @NoEmptyStr(value = "keyword")
    @GetMapping("/getCityByParam")
    public Object getCityByParam(@RequestParam("keyword") String keyword) {
        return baseRegionService.getCityByParam(keyword);
    }
}
