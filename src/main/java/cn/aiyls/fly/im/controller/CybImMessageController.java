package cn.aiyls.fly.im.controller;


import cn.aiyls.fly.entity.CybImMessage;
import cn.aiyls.fly.im.service.ICybImMessageService;
import cn.aiyls.fly.im.vo.NoticeMessageVO;
import cn.aiyls.fly.im.vo.TextMessageVO;
import cn.aiyls.fly.im.vo.UpdateMsgVO;
import cn.aiyls.fly.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * IM消息表 前端控制器
 * </p>
 *
 * @author gu.yuanlin@foundbyte.com
 * @since 2020-12-24
 */
@RestController
@RequestMapping("/open/message")
@Api(tags = "消息controller")
public class CybImMessageController {

    @Autowired
    private ICybImMessageService messageService;

    @GetMapping("/getHistoryByUuid")
    @ApiOperation(value = "根据会话的uuid获取历史消息")
    public Result<List<CybImMessage>> getHistoryByUuid(@RequestParam("uuid") String uuid,
                                                       @RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                       @RequestParam("pageSize") Integer pageSize) {
        List<CybImMessage> history = messageService.getHistoryByUuid(uuid, offset, pageSize);
        return Result.ok(history);

    }

    @PutMapping("/updateMessageRead")
    @ApiOperation(value = "更新消息为已读")
    public Result<Void> updateMessageRead(@RequestBody UpdateMsgVO updateMsgVO) {
        messageService.updateMessageRead(updateMsgVO);
        return Result.ok();
    }

    @PostMapping("/sendMessage")
    @ApiOperation(value = "发送消息，聊天功能的发送消息由ws模式改为http模式，接收消息仍由ws推送，返回消息回执")
    public Result<NoticeMessageVO> sendMessage(@RequestBody TextMessageVO textMessageVO) {
        NoticeMessageVO noticeMessageVO = messageService.sendMessage(textMessageVO);
        return Result.ok(noticeMessageVO);
    }

}
