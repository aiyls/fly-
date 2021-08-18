package cn.aiyls.fly.controller;

import cn.aiyls.fly.aop.CrossDomainWhether;
import cn.aiyls.fly.aop.NoEmptyStr;
import cn.aiyls.fly.service.TFlyBaseRegionService;
import cn.aiyls.fly.service.TFlyFeedbackService;
import cn.aiyls.fly.service.TFlyReportService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/base/open")
public class TFlyBaseController {
    /**
     * 基础位置
     */
    private final TFlyBaseRegionService baseRegionService;
    /**
     * 意见反馈
     */
    private final TFlyFeedbackService feedbackService;
    /**
     * 举报
     */
    private final TFlyReportService reportService;

    @Autowired
    public TFlyBaseController(TFlyBaseRegionService regionService,
                              TFlyFeedbackService feedbackService,
                              TFlyReportService reportService) {
        this.baseRegionService = regionService;
        this.feedbackService = feedbackService;
        this.reportService = reportService;
    }

    /**
     * 创建意见反馈
     * @param params 内容
     * @return
     */
    @PostMapping("/feedback/create")
    public Object createFeedback(@RequestBody JSONObject params) {
        return feedbackService.add(params);
    }

    /**
     * 举报
     * @param params
     * @return
     */
    @PostMapping("/report/create")
    public Object createReport(@RequestBody JSONObject params) {
        return reportService.add(params);
    }

    /**
     * 返回所有地区数据
     * @return
     */
    @GetMapping("/region/getCityOrderByLetter")
    public Object getCityOrderByLetter() {
        return baseRegionService.selectAll();
    }

    /**
     * 检索地区
     * @param keyword
     * @return
     */
    @NoEmptyStr(value = "keyword")
    @GetMapping("/region/getCityByParam")
    public Object getCityByParam(@RequestParam("keyword") String keyword) {
        return baseRegionService.getCityByParam(keyword);
    }
}
