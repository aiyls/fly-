package cn.aiyls.fly.service;

import cn.aiyls.fly.constant.Constant;
import cn.aiyls.fly.entity.User;
import cn.aiyls.fly.enums.ReturnCodes;
import cn.aiyls.fly.enums.UserEnums;
import cn.aiyls.fly.mapper.UserMapper;
import cn.aiyls.fly.redis.RedisUtil;
import cn.aiyls.fly.utils.BaseMethod;
import cn.aiyls.fly.utils.RSAUtil;
import cn.aiyls.fly.utils.Result;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.security.Key;
import java.util.Map;

/**
 * @Author: aiyls
 * @CreateTime: 2021/6/6
 * @Desc:
 */
@Service
@Transactional
public class UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final UserMapper userMapper;
    private final RedisUtil redisUtil;
    @Autowired
    public UserService(UserMapper userMapper, RedisUtil redisUtil) {
        this.userMapper = userMapper;
        this.redisUtil = redisUtil;
    }


    public Object getPublicKey() throws Exception{
        logger.info(" getPublicKey 开始");
        Object getRedisPublicKey = null;
        synchronized (this) {
            getRedisPublicKey = redisUtil.getValue(Constant.RSA, Constant.RSA_PUBLIC_KEY_APP);
            if (ObjectUtils.isEmpty(getRedisPublicKey)) {
                Map<String, Key> map = RSAUtil.initKey();
                String getPublicKey = RSAUtil.getPublicKey(map);
                String getPrivateKey = RSAUtil.getPrivateKey(map);
                redisUtil.setKey(Constant.RSA, Constant.RSA_PUBLIC_KEY_APP, getPublicKey);
                redisUtil.setKey(Constant.RSA, Constant.RSA_PRIVATE_KEY_APP, getPrivateKey);
            }
        }
        JSONObject result = new JSONObject();
        result.put("publicKey", getRedisPublicKey);
        logger.info("getPublicKey结束{}");
        return new Result<Object>(ReturnCodes.success, result);
    }


    /**
     * 注册
     * @param param param{ "phone" , "password", "code"}
     */
    public Object add(JSONObject param){
        logger.info(" add 开始");
        User user = param.toJavaObject(param, User.class);
        user.setStatus(UserEnums.ENABLE.getKey());
        user.setSex(UserEnums.UNKNOWN.getKey());
        userMapper.insert(user);
        logger.info(" add 结束");
        return new Result<Object>(ReturnCodes.success);
    }

    public Object update(JSONObject param){
        logger.info(" update 开始");
        User user = param.toJavaObject(param, User.class);
        userMapper.updateById(user);
        logger.info(" update 结束");
        return new Result<Object>(ReturnCodes.success);
    }

    public Object login(JSONObject param){

        //  验证用户是否存在
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, param.getString("user_name")));
        if (ObjectUtils.isEmpty(user)){
            return new Result<Object>(ReturnCodes.usernameDoesNotExist);
        }
        // 账号锁定
        if (user.getStatus().equals(UserEnums.DISABLE)){
            return new Result<Object>(ReturnCodes.usernameHasBeenLocked);
        }
        // 验证密码
        String privateKey = redisUtil.getValue(Constant.RSA, Constant.RSA_PRIVATE_KEY_APP).toString();
        String decodedPassword = null;
        try {
            String password = param.getString("password");
            byte[] decodedData = RSAUtil.decryptByPrivateKey(password, privateKey);
            decodedPassword = new String(decodedData);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<Object>(ReturnCodes.privateKeyDecryptionError);
        }
        String getPassword = DigestUtils.md5Hex(decodedPassword + user.getSlat());

        // 校验密码
        if (!getPassword.equals(user.getPassword())){
            return new Result<Object>(ReturnCodes.accountPasswordError);
        }
        String token = BaseMethod.getRequest().getSession().getId();
        redisUtil.setKey(token, Constant.TOKEN, token);
        return new Result<Object>(ReturnCodes.success, new User(user));
    }
}
