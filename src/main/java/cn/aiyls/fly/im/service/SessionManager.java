package cn.aiyls.fly.im.service;

import cn.aiyls.fly.im.VO.BaseMessageVO;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

/**
 * @Author: aiyls
 * @CreateTime: 2021/7/5
 * @Desc:
 */
public interface SessionManager {
    // 将session加入管理
    void add(Long key, WebSocketSession session);

    // 根据key移除session管理
    WebSocketSession remove(Long key, String... args);

    // 关闭session
    void close(Long key, WebSocketSession socketSession);

    // 判断用户是否与服务器建立ws连接
    boolean isExist(Long key);

    // 向单个用户发信息
    void sendMessage(Long key, BaseMessageVO baseMessageVO);

    // 向多个用户发信息
    void sendMessage(List<Long> keys, BaseMessageVO baseMessageVO);

    // 向所有用户发信息
    void sendMessage(BaseMessageVO baseMessageVO);
}
