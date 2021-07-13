package cn.aiyls.fly.im.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "编辑（置顶、取消置顶、删除）会话的传参实体")
public class EditSessionVO {

    @ApiModelProperty(value = "会话id")
    private Long id;

    @ApiModelProperty(value = "编辑时间，这个前端不用传")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operatingTime;

    @ApiModelProperty(value = "置顶标识，这个前端不用传")
    private String topFlag;
}
