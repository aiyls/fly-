package cn.aiyls.fly.im.config;

import cn.aiyls.fly.im.service.SessionManager;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: aiyls
 * @CreateTime: 2021/7/6
 * @Desc:
 */
@Slf4j
public class LimitStrategy {
    public enum LimitStrategyEnum{
        LIMIT_ONE,
        NO_LIMIT;
    }

    public SessionManager buildSessionManager(LimitStrategyEnum limitStrategyEnum){
        switch (limitStrategyEnum){
            case NO_LIMIT:
                log.info(" 多端登录无限制");
                return new MultiSessionManager();
            case LIMIT_ONE:
                log.info("单端登录, 互踢模式");
                return new SingleSessionManager();
            default:
                throw new RuntimeException("limit strategy error, please check");
        }
    }
}
