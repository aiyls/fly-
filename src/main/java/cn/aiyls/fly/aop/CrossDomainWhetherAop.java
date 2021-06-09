package cn.aiyls.fly.aop;

import cn.aiyls.fly.enums.ReturnCodes;
import cn.aiyls.fly.utils.BaseMethod;
import cn.aiyls.fly.utils.Result;
import cn.aiyls.fly.utils.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @Author: aiyls
 * @CreateTime: 2021/6/6
 * @Desc:
 */
@Aspect
@Component
public class CrossDomainWhetherAop extends BaseAop{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* cn.aiyls.*.*Controller.*(..))")
    public void crossDomainWhether() {

    }

    @Override
    @Around("crossDomainWhether()")
    @ExceptionHandler(Exception.class)
    public Object around(ProceedingJoinPoint pJoinPoint) {
        logger.info(String.format("========>CrossDomainWhetherAop开始{}"));
        Object[] getArgsValues = null;
        try {
            getArgsValues = getArgsValuesByAop(pJoinPoint);
            Method method = getMethodByAop(pJoinPoint);
            CrossDomainWhether crossDomainWhether = method.getAnnotation(CrossDomainWhether.class);
            if(crossDomainWhether == null){//没在Controller的每个方法上加@CrossDomainWhether注解的都允许跨域（目前所有接口都允许跨域）
                BaseMethod.getResponse().setHeader("Access-Control-Allow-Origin", "*");
            }else{
                HttpServletRequest request = BaseMethod.getRequest();
                String getHost = request.getRemoteHost();
                String getPort = request.getServerPort()+"";
                String getOrigin = request.getHeader("Origin");
                int getPosition1 = StringUtil.getCharacterPosition("//",getOrigin, 1);
                int getPosition2 = StringUtil.getCharacterPosition(":",getOrigin, 2);
                String ip = getOrigin.substring(getPosition1 + 2, getPosition2);
                String port = getOrigin.substring(getPosition2 + 1, getOrigin.length());
                if(!port.equals(getPort) || !ip.equals(getHost)){//判断是否跨域
                    BaseMethod.getResponse().setHeader("Access-Control-Allow-Origin", "*");
                    //只有设置了上面这行代码,才能响应跨域请求的结果,否则不能正常响应接口
                    return new Result<Object>(ReturnCodes.crossDomainAccessIsNotAllowed);
                }
            }
            Object o = pJoinPoint.proceed(getArgsValues);
            return o;
        } catch (NoSuchMethodException e) {
            return new Result<Object>(ReturnCodes.crossDomainWhetherAopError);
        } catch (Throwable e) {
            return new Result<>(ReturnCodes.crossDomainWhetherAopError);
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
