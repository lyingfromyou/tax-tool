package com.example.taxtool.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="BonusPermissionsUser对象", description="奖扣权限用户")
public class BonusPermissionsUser extends BaseEntity {

    @ApiModelProperty(value = "奖扣权限id")
    private Long bonusPermissionsId;

    @ApiModelProperty(value = "奖扣人")
    private Long userId;

    @ApiModelProperty(value = "租户id")
    private Long tenantId;




}
