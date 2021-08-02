package cn.aiyls.fly.mapper;

import cn.aiyls.fly.entity.TFlyFeedback;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TFlyFeedbackMapper extends BaseMapper<TFlyFeedback> {
}
