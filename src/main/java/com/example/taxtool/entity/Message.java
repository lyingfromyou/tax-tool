package com.example.taxtool.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Message extends BaseEntity {

    @ApiModelProperty("通知内容")
    private String content;

    @ApiModelProperty("被通知的用户id")
    private Long notifyUserId;

    @ApiModelProperty("业务类型")
    private Integer type;

    @ApiModelProperty("业务id")
    private String contentId;

    @ApiModelProperty("租户id")
    private Long tenantId;

    @ApiModelProperty("0:未读, 1:已读")
    private Integer status;

}
