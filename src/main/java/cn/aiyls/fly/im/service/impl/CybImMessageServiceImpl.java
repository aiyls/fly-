package cn.aiyls.fly.im.service.impl;
import cn.aiyls.fly.entity.CybImMessage;
import cn.aiyls.fly.im.mapper.CybImMessageMapper;
import cn.aiyls.fly.im.service.ICybImMessageService;
import cn.aiyls.fly.im.service.ICybImSessionService;
import cn.aiyls.fly.im.subpubmq.PublishService;
import cn.aiyls.fly.im.vo.CheckSessionVO;
import cn.aiyls.fly.im.vo.NoticeMessageVO;
import cn.aiyls.fly.im.vo.TextMessageVO;
import cn.aiyls.fly.im.vo.UpdateMsgVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * IM消息表 服务实现类
 * </p>
 *
 * @author gu.yuanlin@foundbyte.com
 * @since 2020-12-24
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CybImMessageServiceImpl extends ServiceImpl<CybImMessageMapper, CybImMessage> implements ICybImMessageService {

    @Resource
    private CybImMessageMapper messageMapper;

    @Resource
    private ICybImSessionService sessionService;

    @Resource
    private PublishService publishService;

    @Override
    public List<CybImMessage> getHistoryByUuid(String uuid, Integer offset, Integer pageSize){
        List<CybImMessage> list = messageMapper.getHistoryByUuid(uuid, offset, pageSize);
        Collections.reverse(list);
        return list;
    }

    @Override
    public Integer insertMessageAndReturnId(CybImMessage message) {
        message.setReadFlag("0");
        return messageMapper.insertMessageAndReturnId(message);
    }

    @Override
    public void updateMessageRead(UpdateMsgVO updateMsgVO) {
        messageMapper.updateMessageRead(updateMsgVO);
    }

    @Override
    public NoticeMessageVO sendMessage(TextMessageVO textMessageVO) {
        // 会话检查，如果会话删除了，则恢复（异步）
        CompletableFuture.runAsync(() -> {
            CheckSessionVO checkSessionVO = textMessageVO.becomeCheckSessionVO();
            sessionService.checkSession(checkSessionVO);
        });

        CybImMessage messageEntity = textMessageVO.becomeEntity();
        insertMessageAndReturnId(messageEntity);

        // 给对方发消息（异步）
        CompletableFuture.runAsync(() -> {
            TextMessageVO oppositeTextMessageVO = new TextMessageVO(messageEntity);
            publishService.publish(oppositeTextMessageVO);
        });

        // 返回消息回执
        return new NoticeMessageVO(messageEntity);
    }

}
