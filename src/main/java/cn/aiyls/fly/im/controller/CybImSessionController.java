package cn.aiyls.fly.im.controller;


import cn.aiyls.fly.aop.NoEmptyStr;
import cn.aiyls.fly.utils.JwtUtils;
import cn.aiyls.fly.utils.Result;
import cn.aiyls.fly.im.service.ICybImSessionService;
import cn.aiyls.fly.im.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * IM会话表 前端控制器
 * </p>
 *
 * @author gu.yuanlin@foundbyte.com
 * @since 2020-12-24
 */
@RestController
@RequestMapping("/open/session")
@Api(tags = "会话controller")
public class CybImSessionController {

    @Autowired
    private ICybImSessionService sessionService;

    @Resource
    HttpServletRequest request;

    @GetMapping("/getSessionListByUserId")
    @ApiOperation(value = "根据用户id获取会话列表")
    public Result<List<SessionVO>> getSessionListByUserId(@RequestParam("userId") Long userId) {
        List<SessionVO> resultList = sessionService.getSessionListByUserId(userId);
        return Result.ok(resultList);
    }

    @GetMapping("/getSessionList")
    @ApiOperation(value = "获取当前用户的会话列表")
    public Result<List<SessionVO>> getSessionList() {
        List<SessionVO> resultList = sessionService.getSessionListByUserId(JwtUtils.getUserId(request));
        return Result.ok(resultList);
    }

    @GetMapping("/getAvatarSessionListByUserId")
    @ApiOperation(value = "根据用户id获取会话列表（带头像）")
    public Result<List<AvatarSessionVO>> getAvatarSessionListByUserId(@RequestParam("userId") Long userId,
                                                                @RequestParam("userName") String userName,
                                                                @RequestParam("userPhone") String userPhone,
                                                                @ApiParam("用户角色：用户1；服务提供者2") @RequestParam("userRole") String userRole) {
        List<AvatarSessionVO> resultList = sessionService.getAvatarSessionListByUserId(userId, userName, userPhone, userRole);
        return Result.ok(resultList);
    }

    @NoEmptyStr(value = "userRole")
    @GetMapping("/getAvatarSessionList")
    @ApiOperation(value = "获取当前用户的会话列表（带头像）")
    public Result<List<AvatarSessionVO>> getAvatarSessionList(@ApiParam("用户角色：用户1；服务提供者2") @RequestParam("userRole") String userRole) {
        List<AvatarSessionVO> resultList = sessionService.getAvatarSessionListByUserId(JwtUtils.getUserId(request),
                JwtUtils.getClaimByName(request, JwtUtils.AttributeEnum.USER_NAME),
                JwtUtils.getClaimByName(request, JwtUtils.AttributeEnum.USER_PHONE),
                userRole);
        return Result.ok(resultList);
    }

    @PostMapping("/createSession")
    @ApiOperation(value = "创建两个用户的会话，如果被删除则会恢复")
    public Result<CreateSuccessVO> createSession(@RequestBody CreateSessionVO createSessionVO) {
        CreateSuccessVO createSuccessVO = sessionService.createSession(createSessionVO);
        return Result.ok(createSuccessVO);
    }

    @PutMapping("/topSession")
    @ApiOperation(value = "置顶会话")
    public Result<Void> topSession(@RequestBody EditSessionVO editSessionVO) {
        sessionService.topSession(editSessionVO);
        return Result.ok();
    }

    @PutMapping("/cancelTopSession")
    @ApiOperation(value = "取消置顶会话")
    public Result<Void> cancelTopSession(@RequestBody EditSessionVO editSessionVO) {
        sessionService.cancelTopSession(editSessionVO);
        return Result.ok();
    }

    @DeleteMapping("/deleteSession")
    @ApiOperation(value = "删除会话，可直接删除，无其他前置条件")
    public Result<Void> deleteSession(@RequestParam("id") Long id) {
        sessionService.deleteSession(id);
        return Result.ok();
    }

}
