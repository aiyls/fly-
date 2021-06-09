package cn.aiyls.fly.aop;


import cn.aiyls.fly.enums.ReturnCodes;
import cn.aiyls.fly.utils.BaseMethod;
import cn.aiyls.fly.utils.Result;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: aiyls
 * @CreateTime: 2021/6/5
 * @Desc:
 */

@Aspect
@Component
public class ValidateAop extends BaseAop{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* cn.aiyls.*.*Controller.*(..))")
    private void validateParameter() {

    }


    @Override
    @Around("validateParameter()")
    @ExceptionHandler(Exception.class)
    public Object around(ProceedingJoinPoint pJoinPoint) {
        logger.info(String.format("========>ValidateAop开始{}"));
        String[] getMethodParamNames = null;
        Object[] getArgsValues = null;
        try {
            getMethodParamNames = getParameterNamesByAop(pJoinPoint);
            getArgsValues = getArgsValuesByAop(pJoinPoint);

            //如果没有参数,进入下一个AOP
            if(getArgsValues.length == 0){
                Object o = pJoinPoint.proceed(getArgsValues);
                return o;
            }

            Method method = getMethodByAop(pJoinPoint);

            //获取参数不能为空标识
            NoEmptyStr noEmptyStr = method.getAnnotation(NoEmptyStr.class);
            //参数不能为空数组
            String[] noEmptyStrArray = new String[]{};
            if(noEmptyStr != null){
                String getValue = noEmptyStr.value();
                noEmptyStrArray = getValue.split(",");
            }

            //参数为JSONObject单独验证,进入下一个AOP
            if (getArgsValues[0] instanceof JSONObject) {
                if (noEmptyStrArray.length != 0) {//参数不能为空数组不为空
                    JSONObject jsonObject = (JSONObject) getArgsValues[0];
                    for (int i = 0; i < noEmptyStrArray.length; i++) {
                        String getNoEmptyStr = noEmptyStrArray[i];
                        if (ObjectUtils.isEmpty(jsonObject.getString(getNoEmptyStr))) {//校验参数不能为空
                            return new Result<>(ReturnCodes.parameterIsEmpty.getCode(), ReturnCodes.parameterIsEmpty.getTips() + "为空的参数为:'" + getNoEmptyStr + "'");
                        }
                    }
                }
                Object o = pJoinPoint.proceed(getArgsValues);
                return o;
            }

            //获取参数非必需标识
            NoCheckParams noCheckParams = method.getAnnotation(NoCheckParams.class);
            //非必需参数名数组
            String[] noCheckParamsArray = new String[]{};
            if(noCheckParams != null){
                String getValue = noCheckParams.value();
                noCheckParamsArray = getValue.split(",");
            }

            if(noCheckParamsArray.length == 0){//没有非必需参数（都是必需参数）
                for(int i = 0;i < getMethodParamNames.length;i++){
                    Object argsValue = getArgsValues[i];
                    if(StringUtils.isEmpty(argsValue)){//校验必需参数非空
                        return new Result<Object>(ReturnCodes.parameterIsEmpty.getCode(), ReturnCodes.parameterIsEmpty.getTips() + "为空的参数为:'" + getMethodParamNames[i] + "'");
                    }
                }
            }else{//有非必需参数
                for(int i = 0;i < getMethodParamNames.length;i++){
                    Object argsValue = getArgsValues[i];
                    List<String> noCheckParamsList = Arrays.asList(noCheckParamsArray);
                    if(!noCheckParamsList.contains(getMethodParamNames[i])){//必需参数
                        if(ObjectUtils.isEmpty(argsValue)){//必需参数可以为空
                            return new Result<Object>(ReturnCodes.parameterIsEmpty.getCode(), ReturnCodes.parameterIsEmpty.getTips() + "为空的参数为:'" + getMethodParamNames[i] + "'");
                        }
                    }
                }
            }
            Object o = pJoinPoint.proceed();
            return o;
        } catch (NoSuchMethodException e) {
            logger.info(String.format("========>ValidateAop异常NoSuchMethodException获取到当前状态码为{}%s", BaseMethod.getResponse().getStatus()));
            logger.info(String.format("========>ValidateAop异常NoSuchMethodException{}%s", e));
            return new Result<Object>(ReturnCodes.validateAopError);
        } catch (Throwable e) {
            logger.info(String.format("========>ValidateAop异常Throwable获取到当前状态码为{}%s", BaseMethod.getResponse().getStatus()));
            logger.info(String.format("========>ValidateAop异常Throwable{}%s", e));
            return new Result<Object>(ReturnCodes.validateAopError);
        }
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
