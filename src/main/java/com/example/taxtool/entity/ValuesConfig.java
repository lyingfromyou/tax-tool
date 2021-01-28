package com.example.taxtool.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value = "ValuesConfig对象", description = "价值观配置")
public class ValuesConfig extends BaseEntity {

    @NotBlank(message = "价值观配置名称不能为空")
    @ApiModelProperty("名称")
    private String name;

    @NotNull(message = "价值观配置权重不能为空")
    @ApiModelProperty("权重")
    private Integer weight;

    @ApiModelProperty("租户id")
    private Long tenantId;

    @TableField(exist = false)
    @ApiModelProperty("分数")
    private Double fraction;

}
