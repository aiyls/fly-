package cn.aiyls.fly.controller;

import cn.aiyls.fly.aop.NoEmptyStr;
import cn.aiyls.fly.enums.ReturnCodes;
import cn.aiyls.fly.service.TFlyCompanyService;
import cn.aiyls.fly.service.TFlyVisitorService;
import cn.aiyls.fly.service.UserService;
import cn.aiyls.fly.utils.Result;
import cn.aiyls.fly.utils.StringUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: aiyls
 * @CreateTime: 2021/6/6
 * @Desc:
 */
@RestController
@RequestMapping("/base/open/user")
public class UserController {

    private final UserService userService;

    private final TFlyCompanyService companyService;

    private final TFlyVisitorService visitorService;

    @Autowired
    public UserController(UserService userService, TFlyCompanyService companyService, TFlyVisitorService visitorService) {
        this.userService = userService;
        this.companyService = companyService;
        this.visitorService = visitorService;
    }

    @GetMapping("/getPublicKey")
    public Object getPublicKey() throws Exception {
        return userService.getPublicKey();
    }

    /**
     * 注册
     * @param param
     * @return
     */
    @NoEmptyStr(value = "username,password")
    @PostMapping("/register")
    public Object add(@RequestBody JSONObject param){
        return userService.add(param);
    }

    /**
     * 登录
     * @param param
     * @return
     */
    @NoEmptyStr(value = "password,phone")
    @PostMapping("/login")
    public Object login(@RequestBody JSONObject param) throws IllegalAccessException {
        return userService.login(param);
    }

    /**
     * 查询用户信息
     */
    @GetMapping("/getUserinfo")
    public Object getUserinfo(@RequestParam Long userId) {
        return userService.selectUserinfoById(userId);
    }

    /**
     * 更新个人信息
     * @param param
     * @return
     */
    @NoEmptyStr(value = "id,phone")
    @PostMapping("/update")
    public Object update(@RequestBody JSONObject param){
        return userService.update(param);
    }

    /**
     * 查询个人喜欢的动态
     */
    @GetMapping(value = "/dynamicLikeList")
    public Object dynamicLikeList(@RequestBody JSONObject param) { return userService.selectLikeDynamic(param); }

    /**
     * 增加公司
     */
    @PostMapping(value = "/addCompany")
    public Object addCompany(@RequestBody JSONObject param) { return companyService.add(param); }

    /**
     * 查询公司信息
     */
    @GetMapping(value = "/companyInfo")
    public Object companyInfo() { return companyService.selectByUserId(); }

    /**
     * 修改公司信息
     */
    @PostMapping(value = "/updateCompany")
    public Object updateCompany(@RequestBody JSONObject param) { return companyService.update(param); }

    /**
     * 添加个人身份证
     */
    @PostMapping(value = "/addUserCardInfo")
    public Object addUserCard(@RequestBody JSONObject params) {
        if (StringUtil.isEmpty(params.getString("frontImage"))) {
            return new Result<Object>(ReturnCodes.failed, "身份证正面照不能为空");
        }
        if (StringUtil.isEmpty(params.getString("backImage"))) {
            return new Result<Object>(ReturnCodes.failed, "身份证反面照不能为空");
        }
        if (StringUtil.isEmpty(params.getString("realname"))) {
            return new Result<Object>(ReturnCodes.failed, "真实姓名不能为空");
        }
        if (StringUtil.isEmpty(params.getString("idcard"))) {
            return new Result<Object>(ReturnCodes.failed, "身份证号码不能为空");
        }
        params.put("idcardAuth", 1);
        return userService.update(params);
    }

    /**
     * 设置个人身份证认证是否通过
     */
    @PostMapping(value = "/updateUserCardStatus")
    public Object updateUserCardStatus(@RequestBody JSONObject datas) {
        datas.put("idcardAuth", datas.getInteger("status"));
        return userService.update(datas);
    }

    /**
     * 查询访客
     */
    @GetMapping(value = "/selectVisitor")
    public Object selectVisitor(@RequestBody JSONObject params) {
        return visitorService.selectAll(params);
    }
}
