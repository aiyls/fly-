package cn.aiyls.fly.im.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "更新消息为已读的传参实体")
public class UpdateMsgVO {

    @ApiModelProperty(value = "会话的uuid")
    private String uuid;

    @ApiModelProperty(value = "用户id")
    private Long userId;
}
