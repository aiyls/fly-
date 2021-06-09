package cn.aiyls.fly.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @Author: aiyls
 * @CreateTime: 2021/6/5
 * @Desc:
 */
public abstract class BaseAop implements Ordered {

    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    public HttpServletResponse getResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }

    /**
     * 获取调用的类
     * @param jp
     * @return
     * @throws NoSuchMethodException
     */
    @SuppressWarnings("rawtypes")
    protected Class getClassByAop(JoinPoint jp) throws NoSuchMethodException {
        return jp.getTarget().getClass();
    }

    /**
     * 获取调用的类的类名（简写）
     * @param jp
     * @return
     * @throws NoSuchMethodException
     */
    protected String getClassSimpleNameByAop(JoinPoint jp) throws NoSuchMethodException {
        return jp.getTarget().getClass().getSimpleName();
    }

    /**
     * 获取调用方法
     * @param jp
     * @return
     * @throws NoSuchMethodException
     */
    @SuppressWarnings("unchecked")
    protected Method getMethodByAop(JoinPoint jp) throws NoSuchMethodException {
        MethodSignature ms = (MethodSignature) jp.getSignature();
        //return ms.getMethod();
        return getClassByAop(jp).getMethod(ms.getName(), ms.getParameterTypes());
    }

    /**
     * 获取调用方法的方法名称（简写）
     * @param jp
     * @return
     * @throws NoSuchMethodException
     */
    protected String getMethodSimpleNameByAop(JoinPoint jp) throws NoSuchMethodException {
        MethodSignature ms = (MethodSignature) jp.getSignature();
        return ms.getMethod().getName();
    }

    /**
     * 获取调用方法的参数名称
     * @param jp
     * @return
     * @throws NoSuchMethodException
     */
    protected String[] getParameterNamesByAop(JoinPoint jp) throws NoSuchMethodException {
        CodeSignature cs= (CodeSignature) jp.getSignature();
        return  cs.getParameterNames();
    }

    /**
     * 获取调用方法的参数名称的全称、类型、个数
     * @param jp
     * @return
     * @throws NoSuchMethodException
     */
    @SuppressWarnings("unchecked")
    protected Parameter[] getParametersByAop(JoinPoint jp) throws NoSuchMethodException {
        MethodSignature ms = (MethodSignature) jp.getSignature();
        return getClassByAop(jp).getMethod(ms.getName(), ms.getParameterTypes()).getParameters();
    }

    /**
     * 获取调用方法的参数的值
     * @param jp
     * @return
     * @throws NoSuchMethodException
     */
    protected Object[] getArgsValuesByAop(JoinPoint jp) throws NoSuchMethodException {
        return jp.getArgs();
    }

    public abstract Object around(ProceedingJoinPoint pJoinPoint);
}
