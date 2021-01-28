package com.example.taxtool.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@ApiModel(value = "PerformanceConfig对象", description = "绩效配置")
public class PerformanceConfig extends BaseEntity {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("配置类型, 1:目标管理, 2:能力管理, 3:意愿管理, 4:辅导改进")
    private Integer category;

    @ApiModelProperty("权重")
    private Integer weight;

    @ApiModelProperty("0:菜单, 1:配置, 2:配置菜单, 菜单下可以包含配置和配置菜单," +
            "配置类型下只能配置相关问题和答案," +
            "配置菜单类型下只能包含配置, 且配置必须包含角色")
    private Integer type;

    @ApiModelProperty("父级id")
    private Long parentId;

    @ApiModelProperty("租户id")
    private Long tenantId;

    @ApiModelProperty("角色id, 逗号分隔")
    private String roles;

    @TableField(exist = false)
    @ApiModelProperty("分数")
    private Double fraction;

}
