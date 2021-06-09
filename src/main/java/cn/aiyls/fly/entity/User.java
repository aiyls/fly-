package cn.aiyls.fly.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: aiyls
 * @CreateTime: 2021/6/6
 * @Desc:
 */
@Data
@TableName("t_user")
public class User implements Serializable {
    public static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String userName;

    private String password;

    private String slat;

    private String phone;

    private String email;

    private Integer sex;

    private Date birthday;

    private String brief;

    private String icon;

    @TableLogic
    private Integer status;

    private String remake;

    private Date createTime;

    private Date updateTime;


    public User() {

    }


    public User(User user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.sex = user.getSex();
        this.birthday = user.getBirthday();
        this.brief = user.getBrief();
        this.icon = user.getIcon();
    }
}
