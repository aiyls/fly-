package cn.aiyls.fly.controller;

import cn.aiyls.fly.aop.NoEmptyStr;
import cn.aiyls.fly.service.UserService;
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
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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
     * 更新个人信息
     * @param param
     * @return
     */
    @NoEmptyStr(value = "id,phone")
    @PostMapping("/update")
    public Object update(@RequestBody JSONObject param){
        return userService.update(param);
    }
}
