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

    @GetMapping("/getCityOrderByLetter")
    public Object getCityOrderByLetter() {
        return baseRegionService.selectAll();
    }

    @NoEmptyStr(value = "keyword")
    @PostMapping("/getCityByParam")
    public Object getCityByParam(@RequestBody JSONObject params) {
        return baseRegionService.getCityByParam(params);
    }
}
