package com.example.taxtool.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PerformanceCommitRecord extends BaseEntity {

    @ApiModelProperty(value = "审批人")
    private Long approver;

    @ApiModelProperty(value = "监督人")
    private Long supervisor;

    @ApiModelProperty(value = "审核状态,0:待审核,1:审核完成,2:拒绝")
    private Integer status;

    @ApiModelProperty("租户id")
    private Long tenantId;

}
