package com.example.taxtool.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value="KeyResult对象", description="OKR KeyResult")
public class KeyResult extends BaseEntity {

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "objective Id")
    private Long objectiveId;

    @ApiModelProperty(value = "打分")
    private BigDecimal score;

    @ApiModelProperty(value = "权重")
    private BigDecimal weights;

    @ApiModelProperty(value = "进度")
    private Integer schedule;

    @ApiModelProperty(value = "状态, -1:默认状态, 0:正常, 1:有风险, 2:已延期")
    private Integer status;

    @ApiModelProperty(value = "0:简单模式, 1:高级模式")
    private Integer model;

    @ApiModelProperty(value = "起始值")
    private Integer startingVal;

    @ApiModelProperty(value = "当前值")
    private Integer currentVal;

    @ApiModelProperty(value = "目标值")
    private Integer targetVal;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "租户id")
    private Long tenantId;


}
