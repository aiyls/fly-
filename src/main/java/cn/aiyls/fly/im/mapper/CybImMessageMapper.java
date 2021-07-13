package cn.aiyls.fly.im.mapper;

import cn.aiyls.fly.entity.CybImMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.aiyls.fly.im.vo.UpdateMsgVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * IM消息表 Mapper 接口
 * </p>
 *
 * @author gu.yuanlin@foundbyte.com
 * @since 2020-12-24
 */
@Mapper
public interface CybImMessageMapper extends BaseMapper<CybImMessage> {

    List<CybImMessage> getHistoryByUuid(@Param("uuid") String uuid,
                                        @Param("offset") Integer offset,
                                        @Param("pageSize") Integer pageSizes);

    Integer insertMessageAndReturnId(CybImMessage message);

    void updateMessageRead(UpdateMsgVO updateMsgVO);

    Long getLastIdByUuid(String uuid);

}
