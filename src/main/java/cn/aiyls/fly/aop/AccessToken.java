package cn.aiyls.fly.aop;

import java.lang.annotation.*;

/**
 * @Author: aiyls
 * @CreateTime: 2021/6/6
 * @Desc:
 */

@Documented
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessToken {
}
