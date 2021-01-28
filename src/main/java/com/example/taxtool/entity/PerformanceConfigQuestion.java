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
@ApiModel(value="PerformanceConfigQuestion对象", description="绩效管理问题")
public class PerformanceConfigQuestion extends BaseEntity {

    @NotBlank(message = "标题不能为空")
    @ApiModelProperty(value = "标题")
    private String title;

    @NotNull(message = "绩效管理id不能为空")
    @ApiModelProperty(value = "绩效管理id")
    private Long performanceConfigId;

    @ApiModelProperty("父级id")
    private Long parentId;

    @ApiModelProperty("类型, 0:菜单, 1:问题")
    private Integer type;

}
