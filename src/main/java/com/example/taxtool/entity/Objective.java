package com.example.taxtool.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value="Objective对象", description="OKR Objective")
public class Objective extends BaseEntity {

    @ApiModelProperty("OKR id")
    private Long okrId;

    @ApiModelProperty("周期id")
    private Long periodId;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "进度记录")
    private String progressRecord;

    @ApiModelProperty(value = "租户id")
    private Long tenantId;

    @ApiModelProperty(value = "排序")
    private Integer sort;


}
