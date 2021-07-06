package cn.aiyls.fly.im.VO;

import lombok.Data;

/**
 * @Author: aiyls
 * @CreateTime: 2021/7/6
 * @Desc:
 */
@Data
public class CreateSuccessVO {
//    @ApiModelProperty(value = "会话编号")
    private Long id;

//    @ApiModelProperty(value = "一次会话会有两条数据库记录，关联这两条记录")
    private String uuid;

//    @ApiModelProperty(value = "如果此前有过对话，则返回最后一条消息的id，如果无，返回0")
    private Long lastId;

//    @ApiModelProperty(value = "对方的号码")
    private String oppositePhone;
}
