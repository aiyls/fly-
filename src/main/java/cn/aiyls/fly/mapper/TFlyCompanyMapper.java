package cn.aiyls.fly.mapper;

import cn.aiyls.fly.entity.TFlyCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TFlyCompanyMapper extends BaseMapper<TFlyCompany> {
}
