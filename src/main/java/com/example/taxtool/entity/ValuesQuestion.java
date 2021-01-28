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
@ApiModel(value="ValuesQuestion对象", description="价值观问题")
public class ValuesQuestion extends BaseEntity {

    @NotBlank(message = "标题不能为空")
    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty("租户id")
    private Long tenantId;

    @NotNull(message = "权重不能为空")
    @ApiModelProperty("权重")
    private Integer weight;


}
