package cn.aiyls.fly.aop;

import java.lang.annotation.*;

/**
 * @Author: aiyls
 * @CreateTime: 2021/6/5
 * @Desc: 验证参数不为空
 */

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoEmptyStr {
    String value() default "";
}
