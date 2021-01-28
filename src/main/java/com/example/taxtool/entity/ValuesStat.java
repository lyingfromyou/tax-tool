package com.example.taxtool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ValuesStat implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("价值观总分")
    private Double jzgzf;

    @ApiModelProperty("价值观总分值")
    private Double jzgzfz;

    @ApiModelProperty("创建人")
    private Long createUser;

    @ApiModelProperty("租户id")
    private Long tenantId;

    @ApiModelProperty("部门id")
    private Long deptId;

    @ApiModelProperty("价值观提交记录id")
    private Long recordId;

    @TableField(exist = false)
    @ApiModelProperty("总分排名")
    private Integer zfpm;

    @TableField(exist = false)
    @ApiModelProperty("总分值排名")
    private Integer zfzpm;


}
