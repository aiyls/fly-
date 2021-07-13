package cn.aiyls.fly.im.service;

import cn.aiyls.fly.entity.CybImSession;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.aiyls.fly.entity.CybImSession;
import cn.aiyls.fly.im.vo.*;

import java.util.List;

/**
 * <p>
 * IM会话表 服务类
 * </p>
 *
 * @author gu.yuanlin@foundbyte.com
 * @since 2020-12-24
 */
public interface ICybImSessionService extends IService<CybImSession> {

    /**
     * 获取用户的会话列表
     */
    List<SessionVO> getSessionListByUserId(Long userId);

    /**
     * 获取用户的会话列表带头像
     */
    List<AvatarSessionVO> getAvatarSessionListByUserId(Long userId, String userName, String userPhone, String userRole);

    /**
     * 检查两个用户的会话，如果有删除的会话则会恢复
     */
    List<CybImSession> checkSession(CheckSessionVO checkSessionVO);

    /**
     * 创建两个用户的会话，在创建之前会先检查会话
     */
    CreateSuccessVO createSession(CreateSessionVO createSessionVO);

    /**
     * 恢复两个用户的会话
     */
    void restoreSession(CheckSessionVO checkSessionVO);

    /**
     * 置顶会话
     */
    void topSession(EditSessionVO editSessionVO);

    /**
     * 取消置顶会话
     */
    void cancelTopSession(EditSessionVO editSessionVO);

    /**
     * 删除会话
     */
    void deleteSession(Long id);

}
