package com.example.taxtool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PerformanceStat implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("KPI总分")
    private Double jxzf;

    @ApiModelProperty("KPI总分值")
    private Double jxzfz;

    @ApiModelProperty("创建人")
    private Long createUser;

    @ApiModelProperty("租户id")
    private Long tenantId;

    @ApiModelProperty("部门id")
    private Long deptId;

    @ApiModelProperty("绩效提交记录id")
    private Long recordId;

    @TableField(exist = false)
    @ApiModelProperty("总分排名")
    private Integer zfpm;

    @TableField(exist = false)
    @ApiModelProperty("总分值排名")
    private Integer zfzpm;


}
