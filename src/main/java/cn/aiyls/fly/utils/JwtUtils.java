package cn.aiyls.fly.utils;

import javax.servlet.http.HttpServletRequest;

public class JwtUtils {

    public enum AttributeEnum {
        USER_NAME,
        USER_PHONE
    }

    public static Long getUserId(HttpServletRequest request) {
        return Long.valueOf(132456);
    }

    public static String getClaimByName(HttpServletRequest request, AttributeEnum attributeEnum) {
        if (attributeEnum == AttributeEnum.USER_NAME) {
            return "徐尧";
        }
        return "18275317961";
    }
}
