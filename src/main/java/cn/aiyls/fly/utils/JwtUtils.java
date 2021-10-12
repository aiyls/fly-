package cn.aiyls.fly.utils;

import cn.aiyls.fly.constant.Constant;
import cn.aiyls.fly.entity.User;
import cn.aiyls.fly.redis.RedisUtil;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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
        String token = request.getHeader("authorization");
        if (token.isEmpty()) {
            return Long.valueOf(0);
        }
        User user = (User) redisUtil.getObject(token);
        if (user == null) {
            return Long.valueOf(0);
        }
        return Long.valueOf(user.getId());
    }

    public static String getClaimByName(HttpServletRequest request, AttributeEnum attributeEnum) {
        String token = request.getHeader("authorization");
        if (token.isEmpty()) {
            return "";
        }
        User user = (User) redisUtil.getObject(token);
        if (user == null) {
            return "";
        }
        if (attributeEnum == AttributeEnum.USER_NAME) {
            return user.getUserName();
        }
        return user.getPassword();
    }

    public static User getUser(HttpServletRequest request) {
        User user = null;
        try {
            String token = request.getHeader("authorization");
            if (token.isEmpty()) {
                return null;
            }
            user = (User) redisUtil.getObject(token);
        } catch (Exception e) {
            System.out.print(e.getMessage());
            user = null;
        }
        return user;
    }

}
