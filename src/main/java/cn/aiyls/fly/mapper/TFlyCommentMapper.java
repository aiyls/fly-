package cn.aiyls.fly.mapper;

import cn.aiyls.fly.entity.TFlyComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TFlyCommentMapper extends BaseMapper<TFlyComment> {

}
