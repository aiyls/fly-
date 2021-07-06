package cn.aiyls.fly.im.VO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: aiyls
 * @CreateTime: 2021/7/6
 * @Desc:
 */
@Data
public class EditSessionVO {

//    @ApiModelProperty(value = "会话id")
    private Long id;

//    @ApiModelProperty(value = "编辑时间，这个前端不用传")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime operatingTime;

//    @ApiModelProperty(value = "置顶标识，这个前端不用传")
    private String topFlag;

}
