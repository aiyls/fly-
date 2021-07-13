package cn.aiyls.fly.im.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "创建用户和服务提供者的会话成功后返回给前端的参数")
public class CreateSuccessVO {

    @ApiModelProperty(value = "会话编号")
    private Long id;

    @ApiModelProperty(value = "一次会话会有两条数据库记录，关联这两条记录")
    private String uuid;

    @ApiModelProperty(value = "如果此前有过对话，则返回最后一条消息的id，如果无，返回0")
    private Long lastId;

    @ApiModelProperty(value = "对方的号码")
    private String oppositePhone;
}
