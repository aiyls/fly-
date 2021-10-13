package cn.aiyls.fly.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: aiylsTableName
 * @CreateTime: 2021/6/6
 * @Desc:
 */
@Data
@TableName("t_fly_user")
public class User implements Serializable {
    public static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    private String slat;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 生日
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;

    private String brief;

    /**
     * 头像
     */
    private String icon;

    /**
     * 状态
     */
    @TableLogic
    private Integer status;

    /**
     * 备注
     */
    private String remake;

    /**
     * 创建时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 身份证号码
     */
    private String idcard;

    /**
     * 身份证正面
     */
    private String frontImage;

    /**
     * 身份证反面
     */
    private String backImage;

    /**
     * 等级
     */
    private Integer grade;

    /**
     * 企业认证 0：未认证，1：认证成功，2：认证中，3：认证失败
     */
    private Integer corpAuth;

    /**
     * 身份认证 0：未认证，1：认证成功，2：认证中，3：认证失败
     */
    private Integer idcardAuth;

    /**
     * 消息数量
     */
    private Integer messageNum;

    /**
     * 动态数量
     */
    private Integer dynamicNum;

    /**
     * 访客数量
     */
    private Integer visitor;

    /**
     * 所在城市
     */
    private String address;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSlat() {
        return this.slat;
    }

    public void setSlat(String slat) {
        this.slat = slat;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getSex() {
        return this.sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public LocalDateTime getBirthday() {
        return this.birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getBrief() {
        return this.brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemake() {
        return this.remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public LocalDateTime getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIdcard() {
        return this.idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getFrontImage() {
        return this.frontImage;
    }

    public void setFrontImage(String frontImage) {
        this.frontImage = frontImage;
    }

    public String getBackImage() {
        return this.backImage;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public Integer getGrade() {
        return this.grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getCorpAuth() {
        return this.corpAuth;
    }

    public void setCorpAuth(Integer corpAuth) {
        this.corpAuth = corpAuth;
    }

    public Integer getIdcardAuth() {
        return this.idcardAuth;
    }

    public void setIdcardAuth(Integer idcardAuth) {
        this.idcardAuth = idcardAuth;
    }

    public Integer getMessageNum() {
        return this.messageNum;
    }

    public void setMessageNum(Integer messageNum) {
        this.messageNum = messageNum;
    }

    public Integer getDynamicNum() {
        return this.dynamicNum;
    }

    public void setDynamicNum(Integer dynamicNum) {
        this.dynamicNum = dynamicNum;
    }

    public Integer getVisitor() {
        return this.visitor;
    }

    public void setVisitor(Integer visitor) {
        this.visitor = visitor;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRealname() {
        return this.realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' + ',' +
                "userName='" + userName + '\'' + ',' +
                "password='" + password + '\'' + ',' +
                "slat='" + slat + '\'' + ',' +
                "phone='" + phone + '\'' + ',' +
                "email='" + email + '\'' + ',' +
                "sex='" + sex + '\'' + ',' +
                "birthday='" + birthday + '\'' + ',' +
                "brief='" + brief + '\'' + ',' +
                "icon='" + icon + '\'' + ',' +
                "status='" + status + '\'' + ',' +
                "remake='" + remake + '\'' + ',' +
                "createTime='" + createTime + '\'' + ',' +
                "updateTime='" + updateTime + '\'' + ',' +
                "nickname='" + nickname + '\'' + ',' +
                "idcard='" + idcard + '\'' + ',' +
                "frontImage='" + frontImage + '\'' + ',' +
                "backImage='" + backImage + '\'' + ',' +
                "grade='" + grade + '\'' + ',' +
                "corpAuth='" + corpAuth + '\'' + ',' +
                "idcardAuth='" + idcardAuth + '\'' + ',' +
                "messageNum='" + messageNum + '\'' + ',' +
                "dynamicNum='" + dynamicNum + '\'' + ',' +
                "visitor='" + visitor + '\'' + ',' +
                "address='" + address + '\'' + ',' +
                "realname='" + realname + '\'' +
                '}';
    }

    public User() {

    }

    public User(User model) {
        this.id = model.id;
        this.userName = model.userName;
        this.password = model.password;
        this.slat = model.slat;
        this.phone = model.phone;
        this.email = model.email;
        this.sex = model.sex;
        this.birthday = model.birthday;
        this.brief = model.brief;
        this.icon = model.icon;
        this.status = model.status;
        this.remake = model.remake;
        this.createTime = model.createTime;
        this.updateTime = model.updateTime;
        this.nickname = model.nickname;
        this.idcard = model.idcard;
        this.frontImage = model.frontImage;
        this.backImage = model.backImage;
        this.grade = model.grade;
        this.corpAuth = model.corpAuth;
        this.idcardAuth = model.idcardAuth;
        this.messageNum = model.messageNum;
        this.dynamicNum = model.dynamicNum;
        this.visitor = model.visitor;
        this.address = model.address;
        this.realname = model.realname;
    }

    /**
     * 将Object对象里面的属性和值转化成Map对象
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String,Object>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }
}
