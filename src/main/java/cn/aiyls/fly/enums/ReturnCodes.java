package cn.aiyls.fly.enums;

import lombok.Getter;

/**
 * @Author: aiyls
 * @CreateTime: 2021/6/6
 * @Desc:
 */
@Getter
public enum ReturnCodes {

    success(0,"success","成功"),
    validateAopError(1001,"validate Aop Error","校验参数信息AOP错误"),
    accessTokenAopError(1002,"access Token Aop Error","校验登录信息AOP错误"),
    permissionAopError(1003,"permission Aop Error","校验权限信息AOP错误"),
    crossDomainWhetherAopError(1004,"cross Domain Whether Aop Error","校验跨域信息AOP错误"),
    crossDomainAccessIsNotAllowed(1005,"cross Domain AccessIs Not Allowed","不允许跨域访问"),
    parsingDataError(1006,"parsing Data Error","解析数据错误"),
    requestParameterIsEmpty(1007,"request Parameter Is Empty","请求参数为空"),
    responseResultIsEmpty(1008,"response Result Is Empty","响应数据为空"),
    systemTestInformationDoesNotExist(1009,"system Test Information Does Not Exist","系统测试信息不存在"),
    jsonParsingError(1010,"json Parsing Error","JSON数据解析错误"),
    base64ImageConvertError(1011,"base64 Image Convert Error","base64图片转换错误"),
    //暂未用到
    requestInterfaceError(1012,"request Interface Error","请求第三方接口出错"),

    parameterIsEmpty(2000,"parameter Is Empty;","参数为空;"),
    parameterIsEmptyString(2001,"parameter Is Empty String;","参数是空字符串"),
    accessTokenIsInvalid(2002,"access Token Is Invalid","登录信息已失效,请重新登录"),
    permissionDenied(2003,"permission Denied","权限未定义"),
    requestError(2004,"request Error","HTTP请求出错"),
    redisKeyIsDoesNotExist(2005,"redis Key Is Does Not Exist","Reids键信息不存在"),
    theRequestHasBeenFused(2006,"the Request Has Been Fused","微服务请求被熔断"),

    usernameDoesNotExist(3001,"username Does Not Exist","账号信息不存在"),
    usernameDecryptionFailure(3002,"username Decryption Failure","账号解密失败"),
    accountPasswordError(3003,"account Password Error","账号密码错误"),
    usernameAlreadyExists(3004,"username Already Exists","账号信息已存在"),
    phoneNumberIsInvalid(3005,"phone Number Is Invalid","手机号码无效"),
    emailIsInvalid(3006,"email Is Invalid","邮箱号码无效"),
    usernameHasBeenLocked(3007,"username Has Been Locked","账号已被锁定"),
    logInMoreThanThreeTimes(3008,"log In Error More Than Three Times, please Try Again In Five Minutes","登录错误超过三次，请在五分钟后重试"),
    accountNewPasswordIsTheSameAsTheOldPassword(3009,"account New Password Is The Same The Old Password","新密码和旧密码相同"),
    privateKeyDecryptionError(3010,"private Key Decryption Error","私钥解密错误"),
    roleNameAlreadyExists(3011,"role Name Already Exists","角色名称已存在"),
    roleInformationDoesNotExist(3012,"role Information Does Not Exist","角色信息不存在"),
    platformNameAlreadyExists(3013,"platform Name Already Exists","平台名称已存在"),
    platformInformationDoesNotExist(3014,"platform Information Does Not Exist","平台信息不存在"),
    menuNameAlreadyExists(3015,"menu Name Already Exists","菜单名称已存在"),
    menuInformationDoesNotExist(3016,"menu Information Does Not Exist","菜单信息不存在"),
    menusAreAssociatedWithAtLeastOnePlatform(3017,"menus Are Associated With At Least One Platform","菜单与至少一个平台关联"),

    error(4000,"Error","系统错误"),

    ;

    private int code;

    private String message;

    private String tips;

    ReturnCodes(int code, String message, String tips) {
        this.code = code;
        this.message = message;
        this.tips = tips;
    }
}
