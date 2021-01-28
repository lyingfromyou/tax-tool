package com.example.taxtool.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value="PerformanceConfigQuestionOption对象", description="绩效管理配置问题选项")
public class PerformanceConfigQuestionOption extends BaseEntity {

    @NotBlank(message = "选项内容不能为空")
    @ApiModelProperty(value = "选项内容")
    private String content;

    @NotNull(message = "问题id不能为空")
    @ApiModelProperty(value = "问题id")
    private Long questionId;

    @NotNull(message = "绩效管理配置id为空")
    @ApiModelProperty(value = "绩效管理配置id")
    private Long performanceConfigId;

    @ApiModelProperty(value = "分数")
    private Integer fraction;

}
