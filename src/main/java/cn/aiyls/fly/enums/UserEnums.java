package cn.aiyls.fly.enums;

import lombok.Getter;

/**
 * @Author: aiyls
 * @CreateTime: 2021/6/6
 * @Desc:
 */
@Getter
public enum UserEnums {
    ENABLE(0, "启用")
    ,DISABLE(1, "禁用")

    ,
    MAN(1, "男")
    , WOMAN(2, "女")
    , UNKNOWN(3, "保密")
    ;

    private int key;

    private String value;

    UserEnums(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
