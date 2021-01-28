package com.example.taxtool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "TargetManageRecord对象", description = "目标管理填写记录")
public class TargetManageRecord implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "绩效配置id")
    private Long performanceConfigId;

    @ApiModelProperty(value = "上月工作内容")
    private String lastMonthWorkContent;

    @ApiModelProperty(value = "上月完成情况")
    private String lastMonthCompletion;

    @ApiModelProperty(value = "目标完成率")
    private Integer achievementRate;

    @ApiModelProperty(value = "上月工作自评")
    private String selfEvaluation;

    @ApiModelProperty(value = "本月工作目标")
    private String workGoals;

    @ApiModelProperty(value = "本月工作计划")
    private String workPlan;

    @ApiModelProperty(value = "审核状态,0:待审核,1:审核完成,2:拒绝")
    private Integer status;

    @ApiModelProperty("提交记录id")
    private Long recordId;

    @ApiModelProperty(value = "监督人")
    @TableField(exist = false)
    private Long supervisor;

    @TableField(exist = false)
    @ApiModelProperty(value = "审批人")
    private Long approver;

}
