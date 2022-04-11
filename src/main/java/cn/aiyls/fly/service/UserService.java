package cn.aiyls.fly.service;

import cn.aiyls.fly.constant.Constant;
import cn.aiyls.fly.entity.TFlyCompany;
import cn.aiyls.fly.entity.TFlyUserLikeDynamic;
import cn.aiyls.fly.entity.TFlyVisitor;
import cn.aiyls.fly.entity.User;
import cn.aiyls.fly.enums.ReturnCodes;
import cn.aiyls.fly.enums.UserEnums;
import cn.aiyls.fly.mapper.TFlyUserLikeDynamicMapper;
import cn.aiyls.fly.mapper.TFlyVisitorMapper;
import cn.aiyls.fly.mapper.UserMapper;
import cn.aiyls.fly.redis.RedisUtil;
import cn.aiyls.fly.utils.*;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
    private final TFlyUserLikeDynamicMapper userLikeDynamicMapper;
    private final TFlyVisitorMapper visitorMapper;
    private final RedisUtil redisUtil;
    @Resource
    HttpServletRequest request;
    @Autowired
    public UserService(UserMapper userMapper, RedisUtil redisUtil, TFlyUserLikeDynamicMapper userLikeDynamicMapper, TFlyVisitorMapper visitorMapper) {
        this.userMapper = userMapper;
        this.redisUtil = redisUtil;
        this.userLikeDynamicMapper = userLikeDynamicMapper;
        this.visitorMapper = visitorMapper;
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
        // 判断是否为空
        User notUser = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, user.getUserName()));
        if (!ObjectUtils.isEmpty(notUser)) {
            return new Result<Object>(ReturnCodes.usernameAlreadyExists);
        }
        user.setStatus(UserEnums.ENABLE.getKey());
        user.setSex(UserEnums.UNKNOWN.getKey());
        // 验证密码
        String decodedPassword = null;
        String privateKey = redisUtil.getValue(Constant.RSA, Constant.RSA_PRIVATE_KEY_APP).toString();
        try {
            String password = param.getString("password");
            byte[] decodedData = RSAUtil.encryptByPrivateKey(StringUtil.strConvertByteArray(password), privateKey);
            decodedPassword = new String(decodedData);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<Object>(ReturnCodes.privateKeyDecryptionError);
        }
        String getPassword = DigestUtils.md5Hex(decodedPassword + user.getSlat());
        user.setPassword(getPassword);
        user.setNickname("玖友" + getPassword.substring(2,5));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setBirthday(LocalDateTime.of(1970,1,1,0,0,0));
        userMapper.insert(user);
        logger.info(" add 结束");
        return new Result<Object>(ReturnCodes.success);
    }

    public Object update(JSONObject param){
        User user = param.toJavaObject(param, User.class);
        if (user.getId() == null) {
            user.setId(JwtUtils.getUserId(request));
        }
        user.setUpdateTime(LocalDateTime.now());
        Integer index = userMapper.updateById(user);
        if (index == 0) {
            return new Result<Object>(ReturnCodes.failed, "更新失败");
        }
        return new Result<Object>(ReturnCodes.success);
    }

    public Object login(JSONObject param) throws IllegalAccessException {

        //  验证用户是否存在
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, param.getString("username")));
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
            byte[] decodedData = RSAUtil.encryptByPrivateKey(StringUtil.strConvertByteArray(password), privateKey);
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
        // 时间格式进去缓存不了，准确说能力不够，搞了一天还是没搞通 用这种low的做法吧
        LocalDateTime birthday = user.getBirthday();
        user.setBirthday(null);
        user.setCreateTime(null);
        user.setUpdateTime(null);
        redisUtil.setObject(token, user);
        user.setPassword("");
        user.setBirthday(birthday);
        Map<String, Object> map = User.objectToMap(user);
        map.put("token", token);
        return new Result<Object>(ReturnCodes.success, map);
    }

    /**
     * 查询个人喜欢的动态
     */
    public Object selectLikeDynamic(JSONObject params) {
        // 分页
        IPage<TFlyUserLikeDynamic> page = new Page<>(params.getInteger("pageNum"), params.getInteger("pageSize"));
        // 条件构造器
        QueryWrapper<TFlyUserLikeDynamic> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().orderByAsc(TFlyUserLikeDynamic::getCreateTime,TFlyUserLikeDynamic::getUpdateTime);
        IPage<TFlyUserLikeDynamic> pageList = userLikeDynamicMapper.selectPage(page, queryWrapper);
        return new Result<Object>(ReturnCodes.success,pageList);
    }

    /**
     * 查询用户信息
     */
    public Object selectUserinfoById(Long id) {
        Long userId = id;
        if (id == 0) {
            userId = JwtUtils.getUserId(request);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getId, userId);
        User userinfo = userMapper.selectOne(queryWrapper);
        userinfo.setPassword("");
        Long currentUserId = JwtUtils.getUserId(request);
        if ( 0 != id && currentUserId != userinfo.getId()) {
            // 查询今天是否已经访问了，如果已经访问了就不添加记录了，直跟新一下时间
            QueryWrapper<TFlyVisitor> visitorQueryWrapper = new QueryWrapper<>();
            visitorQueryWrapper.lambda().eq(TFlyVisitor::getStatus, 1);
            visitorQueryWrapper.lambda().eq(TFlyVisitor::getUserId, userinfo.getId());
            visitorQueryWrapper.lambda().ge(TFlyVisitor::getCreateTime, LocalDateTime.of(LocalDate.now(), LocalTime.of(0,0,0)));
            TFlyVisitor currentVisitor = visitorMapper.selectOne(visitorQueryWrapper);
            if (currentVisitor != null) {
                currentVisitor.setCreateTime(LocalDateTime.now());
                visitorMapper.updateById(currentVisitor);
            } else {
                // 别人查询他自己的主页
                TFlyVisitor visitor = new TFlyVisitor();
                visitor.setIcon(userinfo.getIcon());
                visitor.setUsername(StringUtil.isNotEmpty(userinfo.getNickname()) ? userinfo.getNickname() : userinfo.getUserName());
                visitor.setUserId(userinfo.getId());
                visitor.setUserToId(currentUserId);
                visitor.setStatus(1);
                visitor.setCreateTime(LocalDateTime.now());
                visitorMapper.insert(visitor);
            }

        }
        if (userinfo == null) {
            return new Result<Object>(ReturnCodes.failed, "没有数据");
        }
        return new Result<Object>(ReturnCodes.success, userinfo);
    }
}
