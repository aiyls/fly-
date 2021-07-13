package cn.aiyls.fly.im.service;

import cn.aiyls.fly.entity.CybImMessage;
import cn.aiyls.fly.im.vo.NoticeMessageVO;
import cn.aiyls.fly.im.vo.TextMessageVO;
import cn.aiyls.fly.im.vo.UpdateMsgVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * IM消息表 服务类
 * </p>
 *
 * @author gu.yuanlin@foundbyte.com
 * @since 2020-12-24
 */
public interface ICybImMessageService extends IService<CybImMessage> {

    List<CybImMessage> getHistoryByUuid(String uuid, Integer pageNum, Integer pageSize);

    Integer insertMessageAndReturnId(CybImMessage message);

    void updateMessageRead(UpdateMsgVO updateMsgVO);

    NoticeMessageVO sendMessage(TextMessageVO textMessageVO);

}
