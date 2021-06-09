package cn.aiyls.fly.aop;

import java.lang.annotation.*;

/**
 * @Author: aiyls
 * @CreateTime: 2021/6/6
 * @Desc:
 */

@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JudgeToken {

    String value() default "";

    String type() default "";
}
