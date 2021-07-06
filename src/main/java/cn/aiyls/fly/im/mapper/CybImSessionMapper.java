package cn.aiyls.fly.im.mapper;

import cn.aiyls.fly.entity.CybImSession;
import cn.aiyls.fly.im.VO.CheckSessionVO;
import cn.aiyls.fly.im.VO.EditSessionVO;
import cn.aiyls.fly.im.VO.SessionVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: aiyls
 * @CreateTime: 2021/7/6
 * @Desc:
 */
@Mapper
@Component
public interface CybImSessionMapper extends BaseMapper<CybImSession> {

    /**
     * 获取用户的会话列表
     */
    List<SessionVO> getSessionListByUserId(Long userId);

    /**
     * 检查两个用户的会话，返回会话的数量
     */
    List<CybImSession> checkSession(CheckSessionVO checkSessionVO);

    /**
     * 恢复用户的会话，如果有两个用户的名字则会更新两个用户的名字
     */
    void restoreSession(CheckSessionVO checkSessionVO);

    /**
     * 恢复对方用户的会话，如果有两个用户的名字则会更新两个用户的名字
     */
    void restoreOppositeSession(CheckSessionVO checkSessionVO);

    /**
     * 删除会话
     */
    void deleteSession(EditSessionVO editSessionVO);

    /**
     * 改变会话置顶状态，置顶 or 取消置顶
     */
    void changeTopSession(EditSessionVO editSessionVO);

    Integer insertSessionAndReturnId(CybImSession session);
}
