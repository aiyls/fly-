package cn.aiyls.fly.im.config;


import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * @Author: aiyls
 * @CreateTime: 2021/7/5
 * @Desc:
 */
@Slf4j
@Component
public class MyInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        log.info("start shake hands");
        // 获得请求参数
        Map<String, String> paramMap = HttpUtil.decodeParamMap(serverHttpRequest.getURI().getQuery(), Charset.defaultCharset());
        String userId = paramMap.get("userId");
        if (StrUtil.isNotBlank(userId) && NumberUtil.isLong(userId)) {
            // 放入属性域
            map.put("userId", Long.parseLong(userId));
            log.info("user " + userId + " shake hands success");
            return true;
        } else {
            log.info("invalid user, shake hands failure");
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        log.info("shake hands has completed");
    }
}
