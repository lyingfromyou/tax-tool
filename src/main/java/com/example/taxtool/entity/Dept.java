package com.example.taxtool.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "部门")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Dept extends BaseEntity {

    @NotBlank(message = "部门名称不能为空")
    @ApiModelProperty("部门名称")
    private String name;

    @ApiModelProperty("排序值")
    private Integer sort;

    @NotNull(message = "需要指定部门主管")
    @ApiModelProperty("部门主管")
    private Long managerId;

    @ApiModelProperty("父级部门id")
    private Long parentId;

    @ApiModelProperty("租户id")
    private Long tenantId;

}
