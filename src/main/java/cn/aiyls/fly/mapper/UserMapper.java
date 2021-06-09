package cn.aiyls.fly.mapper;

import cn.aiyls.fly.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Author: aiyls
 * @CreateTime: 2021/6/6
 * @Desc:
 */
@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {

}
