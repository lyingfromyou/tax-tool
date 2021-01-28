package com.example.taxtool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class BaseEntity implements Serializable {

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
