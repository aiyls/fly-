package cn.aiyls.fly.utils;

import cn.aiyls.fly.constant.Constant;
import cn.aiyls.fly.entity.User;
import cn.aiyls.fly.redis.RedisUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.MimeHeaders;

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
        Object userString = redisUtil.getValue(token, Constant.TOKEN);
        if (userString == null) {
            return Long.valueOf(0);
        }
        JSONObject jsonObject=JSONObject.fromObject(userString);
        return Long.valueOf(jsonObject.getString("id"));
    }

    public static String getClaimByName(HttpServletRequest request, AttributeEnum attributeEnum) {
        String token = request.getHeader("authorization");
        if (token.isEmpty()) {
            return "";
        }
        Object userString = redisUtil.getValue(token, Constant.TOKEN);
        if (userString == null) {
            return "";
        }
        JSONObject jsonObject=JSONObject.fromObject(userString);
        if (attributeEnum == AttributeEnum.USER_NAME) {
            return jsonObject.getString("userName");
        }
        return jsonObject.getString("password");
    }

    public static User getUser(HttpServletRequest request) {
        String token = request.getHeader("authorization");
        if (token.isEmpty()) {
            return null;
        }
        Object userString = redisUtil.getValue(token, Constant.TOKEN);
        if (userString == null) {
            return null;
        }
        JSONObject jsonObject=JSONObject.fromObject(userString);
        User user = (User) JSONObject.toBean(jsonObject, User.class);
        return user;
    }

}
