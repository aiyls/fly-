package cn.aiyls.fly.im.mywebsocket;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LimitStrategy {

    public enum LimitStrategyEnum {
        LIMIT_ONE,
        NO_LIMIT
    }

    public SessionManager buildSessionManager(LimitStrategyEnum limitStrategyEnum) {
        switch (limitStrategyEnum) {
            case LIMIT_ONE:
                log.info("单端登陆，互踢模式");
                return new SingleSessionManager();
            case NO_LIMIT:
                log.info("多端登陆无限制");
                return new MultiSessionManager();
            default:
                throw new RuntimeException("limit strategy error, please check");
        }
    }

}
