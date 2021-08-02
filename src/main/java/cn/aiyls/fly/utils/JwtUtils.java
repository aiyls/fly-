package cn.aiyls.fly.utils;

import cn.aiyls.fly.constant.Constant;
import cn.aiyls.fly.redis.RedisHashUtils;
import cn.aiyls.fly.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtUtils {

    private static RedisUtil redisUtil;

    public enum AttributeEnum {
        USER_NAME,
        USER_PHONE
    }

    @Autowired
    public JwtUtils(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    public static Long getUserId(HttpServletRequest request) {
        return Long.valueOf(132456);
    }

    public static String getClaimByName(HttpServletRequest request, AttributeEnum attributeEnum) {
        String token = request.getHeader("authorization");
        if (attributeEnum == AttributeEnum.USER_NAME) {
            return redisUtil.getValue(token, Constant.TOKEN).toString();
        }

        return "18275317961";
    }
}
