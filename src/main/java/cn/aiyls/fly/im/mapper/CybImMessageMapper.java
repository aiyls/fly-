package cn.aiyls.fly.im.mapper;

import cn.aiyls.fly.entity.CybImMessage;
import cn.aiyls.fly.im.VO.UpdateMsgVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: aiyls
 * @CreateTime: 2021/7/6
 * @Desc:
 */
@Mapper
@Component
public interface CybImMessageMapper extends BaseMapper<CybImMessage> {

    List<CybImMessage> getHistoryByUuid(@Param("uuid") String uuid,
                                        @Param("offset") Integer offset,
                                        @Param("pageSize") Integer pageSizes);

    Integer insertMessageAndReturnId(CybImMessage message);

    void updateMessageRead(UpdateMsgVO updateMsgVO);

    Long getLastIdByUuid(String uuid);
}
