package cn.aiyls.fly.enums;

import lombok.Getter;

/**
 * @Author: aiyls
 * @CreateTime: 2021/6/6
 * @Desc:
 */
@Getter
public enum TokenTypeEnum {

    NO_TOKEN(2, "noToken"),
    ;

    private Integer key;

    private String value;

    TokenTypeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }
}
