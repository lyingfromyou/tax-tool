package com.example.taxtool.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="BonusPermissions对象", description="奖扣权限")
public class BonusPermissions extends BaseEntity {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "分数权限")
    private Integer score;

    @ApiModelProperty(value = "租户id")
    private Long tenantId;

}
