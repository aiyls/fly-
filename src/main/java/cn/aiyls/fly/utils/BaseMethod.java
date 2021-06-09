package cn.aiyls.fly.utils;

import cn.aiyls.fly.constant.Constant;
import cn.aiyls.fly.enums.AdminEnum;
import cn.aiyls.fly.enums.ReturnCodes;
import cn.aiyls.fly.redis.RedisUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: aiyls
 * @CreateTime: 2021/6/6
 * @Desc:
 */
public class BaseMethod {

    public static JSONObject getDataJsonByResponseJson(Result<Object> result){
        return (JSONObject) JSONObject.toJSON(result.getData());
    }

    public static JSONArray getDataInfoJsonByResponseJson(Result<Object> result){
        JSONObject json = (JSONObject) JSONObject.toJSON(result.getData());
        return json.getJSONArray(Constant.LIST);
    }

    public static Integer getDataTotalCountByResponseJson(Result<Object> result){
        JSONObject json = (JSONObject) JSONObject.toJSON(result.getData());
        return json.getInteger(Constant.TOTAL_COUNT);
    }

    public static JSONArray getDataArrayJsonByResponseJson(Result<Object> result) {
        return  (JSONArray) JSONObject.toJSON(result.getData());
    }

    public static Result<Object> isSuperAdmin(RedisUtil redisUtil, String token) {
        Integer type = Integer.parseInt(redisUtil.getValue(token, Constant.TYPE).toString());
        if(AdminEnum.SUPER_ADMIN.getKey() == type) {
            return new Result<Object>(ReturnCodes.success);
        }else {
            return new Result<Object>(ReturnCodes.permissionDenied);
        }
    }

    public static Boolean isSuperAdminJust(RedisUtil redisUtil,String token) {
        Integer type = Integer.parseInt(redisUtil.getValue(token, Constant.TYPE).toString());
        if(AdminEnum.SUPER_ADMIN.getKey() == type) {
            return true;
        }else {
            return false;
        }
    }



    public static Result<Object> checkParams(JSONObject paramsJson,String checkParams,
                                                   RedisUtil redisUtil) {
        if(paramsJson == null) {
            return new Result<Object>(ReturnCodes.requestParameterIsEmpty);
        }
        String[] checkParamsArray = checkParams.split(",");
        for (int i = 0; i < checkParamsArray.length; i++) {
            String checkParam = checkParamsArray[i];
            Object getCheckParam = paramsJson.get(checkParam);
            if(ObjectUtils.isEmpty(getCheckParam)) {
                return new Result<>(ReturnCodes.parameterIsEmpty.getCode(), ReturnCodes.parameterIsEmpty.getMessage() + " it's '" + checkParam + "'");
            }else if (Constant.TOKEN.equals(checkParam)) {
                if(!redisUtil.isExists(getCheckParam.toString())) {
                    return new Result<Object>(ReturnCodes.accessTokenIsInvalid);
                }
            }
        }
        return new Result<Object>(ReturnCodes.success);
    }

    public static Result<Object> responseResult(String result) {
        if(ObjectUtils.isEmpty(result)){
            return new Result<Object>(ReturnCodes.requestError);
        }
        JSONObject resultJson = JSONObject.parseObject(result);
        JSONObject resultInfo = resultJson.getJSONObject(Constant.RESULT);
        if(resultInfo == null) {
            return new Result<Object>(ReturnCodes.error, resultJson);
        }
        Integer getResult = resultInfo.getInteger(Constant.CODE);
        if (getResult != 200) {
            return new Result<Object>(getResult,resultJson.getString(Constant.RESULT));
        }
        return new Result<Object>(ReturnCodes.success, resultJson);
    }

    public static Result<Object> responseResult(Integer code,String message) {
        return new Result<Object>(code, message);
    }

    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return request;
    }

    public static HttpServletResponse getResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        return response;
    }

    public static String getUrl(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        return url;
    }

    public static String getUri(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return uri;
    }

    public static String getResultByInputStream(InputStream in) throws IOException {
        String result = IOUtils.toString(in, Constant.UTF8);
        return result;
    }

    public static JSONObject getResultJsonByInputStream(InputStream in) throws IOException {
        String result = IOUtils.toString(in, Constant.UTF8);
        if(ObjectUtils.isEmpty(result)) {
            return null;
        }
        return JSONObject.parseObject(result);
    }

    public static Integer getUserId(RedisUtil redisUtil,String token) {
        Integer userId = Integer.parseInt(redisUtil.getValue(token, Constant.USER_ID).toString());
        return userId;
    }

    public static String getUsername(RedisUtil redisUtil,String token) {
        Object usernameValue = redisUtil.getValue(token, Constant.USERNAME);
        if(!ObjectUtils.isEmpty(usernameValue)) {
            return usernameValue.toString();
        }else {
            Object accountValue = redisUtil.getValue(token, Constant.ACCOUNT);
            if(!ObjectUtils.isEmpty(accountValue)) {
                return accountValue.toString();
            }else {
                return null;
            }
        }
    }

    public static String getMerchantName(RedisUtil redisUtil,String token) {
        Object getMerchantName = redisUtil.getValue(token, Constant.MERCHANT_NAME);
        if(!ObjectUtils.isEmpty(getMerchantName)) {
            return getMerchantName.toString();
        }else {
            return null;
        }
    }

    public static String getTeacherAdminAccount(RedisUtil redisUtil,String token) {
        Object usernameValue = redisUtil.getValue(token, Constant.ADMIN_ACCOUNT);
        if(!ObjectUtils.isEmpty(usernameValue)) {
            return usernameValue.toString();
        }else {
            return null;
        }
    }

    public static String getAccount(RedisUtil redisUtil,String token) {
        Object accountValue = redisUtil.getValue(token, Constant.ACCOUNT);
        if(ObjectUtils.isEmpty(accountValue)) {
            return accountValue.toString();
        }else {
            Object usernameValue = redisUtil.getValue(token, Constant.USERNAME);
            if(!ObjectUtils.isEmpty(accountValue)) {
                return usernameValue.toString();
            }else {
                return null;
            }
        }
    }

    public static Object getName(RedisUtil redisUtil, String token) {
        String name = redisUtil.getValue(token, Constant.NAME).toString();
        return name;
    }

    public static String getToken(JSONObject paramJson) {
        String token = paramJson.getString(Constant.ACCESS_TOKEN);
        return token;
    }

    public static String getSessionId(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return null;
        }
        for (Cookie c : cookies) {
            String remoteSession = c.getName();
            if (Constant.REMOTE_SESSION.equals(remoteSession)) {
                return c.getValue();
            }
            if (Constant.JSESSIONID.equals(remoteSession)) {
                return c.getValue();
            }
        }
        return null;
    }
}
