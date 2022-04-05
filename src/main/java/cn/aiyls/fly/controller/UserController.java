package cn.aiyls.fly.controller;

import cn.aiyls.fly.aop.NoEmptyStr;
import cn.aiyls.fly.entity.User;
import cn.aiyls.fly.service.TFlyCompanyService;
import cn.aiyls.fly.service.UserService;
import com.alibaba.fastjson.JSON;
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

    @Autowired
    public UserController(UserService userService, TFlyCompanyService companyService) {
        this.userService = userService;
        this.companyService = companyService;
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
        params.put("idcardAuth", 2);
        return userService.update(params);
    }

    /**
     * 设置个人身份证认证是否通过
     */
    @PostMapping(value = "/updateUserCardStatus")
    public Object updateUserCardStatus(@RequestBody JSONObject params) {
        params.put("idcardAuth", params.getInteger("status"));
        return userService.update(params);
    }
}
