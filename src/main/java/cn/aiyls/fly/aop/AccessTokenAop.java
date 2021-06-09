package cn.aiyls.fly.aop;

import cn.aiyls.fly.constant.Constant;
import cn.aiyls.fly.enums.ReturnCodes;
import cn.aiyls.fly.enums.TokenTypeEnum;
import cn.aiyls.fly.redis.RedisUtil;
import cn.aiyls.fly.utils.BaseMethod;
import cn.aiyls.fly.utils.Result;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author: aiyls
 * @CreateTime: 2021/6/6
 * @Desc:
 */

@Aspect
@Component
public class AccessTokenAop extends BaseAop{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final RedisUtil redisUtil;
    @Autowired
    public AccessTokenAop(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Pointcut("execution(* cn.aiyls.*.*Controller.*(..))")
    public void validateAccessToken(){}

    @Override
    @Around("validateAccessToken()")
    public Object around(ProceedingJoinPoint pJoinPoint) {
        logger.info(String.format("========>AccessTokenAop开始{}"));
        String[] getMethodParamNames = null;
        Object[] getArgsValues = null;
        try {
            getMethodParamNames = getParameterNamesByAop(pJoinPoint);
            getArgsValues = getArgsValuesByAop(pJoinPoint);

            //如果没有参数,进入下一个AOP
            if (getArgsValues.length == 0) {
                Object o = pJoinPoint.proceed(getArgsValues);
                return o;
            }

            Method method = getMethodByAop(pJoinPoint);
            JudgeToken judgeToken = method.getAnnotation(JudgeToken.class);
            if(judgeToken != null) {
                String tokenType = judgeToken.type();
                if(TokenTypeEnum.NO_TOKEN.getValue().equals(tokenType)){
                    return pJoinPoint.proceed(getArgsValues);
                }
            }

            //参数为JSONObject单独验证,进入下一个AOP
            JSONObject jsonObject = null;
            if (getArgsValues[0] instanceof JSONObject) {
                jsonObject = (JSONObject) getArgsValues[0];
                if (!redisUtil.isExists(jsonObject.getString(Constant.TOKEN))) {
                    return new Result<Object>(ReturnCodes.accessTokenIsInvalid);
                }
                Object o = pJoinPoint.proceed(getArgsValues);
                return o;//结束程序
            }

            for (int i = 0; i < getMethodParamNames.length; i++) {
                if (Constant.TOKEN.equals(getMethodParamNames[i])) {
                    String getArgsValuesString = (String)getArgsValues[i];
                    if (!redisUtil.isExists(getArgsValuesString)) {
                        return new Result<Object>(ReturnCodes.accessTokenIsInvalid);
                    }
                }
            }
            return  pJoinPoint.proceed();
        } catch (NoSuchMethodException e) {
            logger.info(String.format("========>AccessTokenAop异常NoSuchMethodException获取到当前状态码为{}%s", BaseMethod.getResponse().getStatus()));
            logger.info(String.format("========>AccessTokenAop异常NoSuchMethodException{}%s", e));
            return new Result<Object>(ReturnCodes.accessTokenAopError);
        } catch (Throwable e) {
            logger.info(String.format("========>AccessTokenAop异常Throwable获取到当前状态码为{}%s", BaseMethod.getResponse().getStatus()));
            logger.info(String.format("========>AccessTokenAop异常Throwable{}%s", e));
            return new Result<Object>(ReturnCodes.accessTokenAopError);
        }
    }

    @Override
    public int getOrder() {
        return 3;
    }

    public String getToken(String[] getMethodParamNames,Object[] getArgsValues) {
        String token = null;
        for (int i = 0; i < getMethodParamNames.length; i++) {
            if (Constant.TOKEN.equals(getMethodParamNames[i])) {
                token = (String)getArgsValues[i];
            }
        }
        return token;
    }
}
