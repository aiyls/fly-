package cn.aiyls.fly.enums;

import lombok.Getter;

/**
 * @Author: aiyls
 * @CreateTime: 2021/6/6
 * @Desc:
 */
@Getter
public enum AdminEnum {
    SUPER_ADMIN(1,"超级管理员"),
    GENERAL_ADMIN(2,"普通管理员")
    ;
    private Integer key;
    private String value;

    AdminEnum(Integer key,String value){
        this.key = key;
        this.value = value;
    }
}
