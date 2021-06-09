package cn.aiyls.fly.constant;

/**
 * @Author: aiyls
 * @CreateTime: 2021/6/6
 * @Desc:
 */
public class Constant {

    //加密模块
    public static final String RSA = "rsa";
    public static final String RSA_PUBLIC_KEY = "publicKey";
    public static final String RSA_PRIVATE_KEY = "privateKey";
    public static final String RSA_PUBLIC_KEY_APP = "publicKeyApp";
    public static final String RSA_PRIVATE_KEY_APP = "privateKeyApp";

    //升序
    public static final String ASC = "asc";
    //降序
    public static final String DESC = "desc";

    public static final String TOKEN = "token";
    public static final String ACCESS_TOKEN = "accessToken";
    public static final String NAME = "name";
    public static final String TYPE = "type";
    public static final String USER_ID = "userId";
    public static final String ACCOUNT = "account";
    public static final String USERNAME = "username";
    public static final String USER_PHONE = "userPhone";
    public static final String USER_TYPE = "userType";
    public static final String USER_NAME = "userName";
    public static final String MERCHANT_NAME = "merchan_name";
    public static final String SUPERADMIN = "superadmin";
    public static final String ADMIN_ACCOUNT = "adminAccount";
    public static final String INFO = "info";
    public static final String LIST = "list";
    public static final String TOTAL_COUNT = "totalCount";
    public static final String CODE = "code";
    public static final String RESULT = "result";
    public static final String UTF8 = "UTF-8";
    public static final String JSESSIONID = "JSESSIONID";
    public static final String REMOTE_SESSION = "remoteSession";
    public static final String SF_FILE_SEPARATOR = System.getProperty("file.separator");//文件分隔符
    public static final String SUCCESS = "SUCCESS";
    public static final String FAIL = "fail";
    public static final String OK = "OK";

    //后台重置密码
    public static final String INIT_PASSWORD = "123456";
    //APP默认密码
    public static final String INIT_APP_PASSWORD = "000000";

    //zuul拦截URL
    public static final String SYSTEM_HELLO = "/hello";
    public static final String SWAGGER_URL = "/v2/api-docs";
    public static final String API_GET_PUBLIC_KEY = "/getPublicKey";
    public static final String HEALTH_WEB = "/healthweb";
    public static final String MERCHANT_WEB = "/merchantweb";
    public static final String HEALTH_TOOL = "health-tool";
    public static final String SYSTEM_LOG = "/system/log";
    public static final String DOWNLOAD = "/download";
    public static final String SYSTEM_INDEX_URL = "/healthweb/index.html";
    public static final String SYSTEM_LOGIN_URL = "/healthweb/login.html";
    public static final String SYSTEM_ALL_LOGIN_URL = "/healthweb/mainos/login.html";


    //修改手机号  旧手机号 新手机号
    public static final String USER_OLD_PHONE = "oldPhone";
    public static final String USER_NEW_PHONE = "newPhone";

    //redis 验证码常量
    public static final String APP_CHANGE_PHONE = "app_change_phone";
}
