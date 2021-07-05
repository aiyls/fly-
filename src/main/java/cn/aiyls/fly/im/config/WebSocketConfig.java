package cn.aiyls.fly.im.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @Author: aiyls
 * @CreateTime: 2021/7/5
 * @Desc:
 */

@Configuration
public class WebSocketConfig implements WebSocketConfigurer {

    private final MyHandler myHandler;
    private final MyInterceptor myInterceptor;
    @Autowired
    public WebSocketConfig(MyHandler myHandler, MyInterceptor myInterceptor) {
        this.myHandler = myHandler;
        this.myInterceptor = myInterceptor;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myHandler, "/open/websocket").addInterceptors(myInterceptor).setAllowedOrigins("*");
    }
}
