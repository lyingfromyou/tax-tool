package com.example.taxtool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
public class Menu implements Serializable {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty("菜单id")
    private Integer menuId;

    @NotBlank
    @ApiModelProperty("菜单名")
    private String name;

    @ApiModelProperty(value = "菜单权限标识")
    private String permission;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("菜单父id")
    private Integer parentId;

    @ApiModelProperty("排序值")
    private Integer sort;

    @ApiModelProperty("创建人")
    private Long createUser;

    @ApiModelProperty("更新人")
    private Long updateUser;

    @ApiModelProperty("删除人")
    private Long deleteUser;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("删除时间")
    private Date deleteTime;

    @ApiModelProperty("是否已删除, 0-否, 1-是")
    private Integer deleted;

}
