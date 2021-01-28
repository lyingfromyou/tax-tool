package com.example.taxtool.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ApiModel(value="TaskReminder对象", description="任务提醒")
public class TaskReminder extends BaseEntity {

    @NotBlank(message = "标题不能为空")
    @ApiModelProperty(value = "提醒标题")
    private String title;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "0:待汇报, 1:待审核, 2:已完成, 3:已取消")
    private Integer status;

    @NotNull(message = "截止时间不能为空")
    @ApiModelProperty(value = "截止时间")
    private Date endTime;

    @NotNull(message = "提醒类型不能为空")
    @ApiModelProperty(value = "提醒类型, 0:一次性, 1:每日, 2: 每周, 3:每月")
    private Integer type;

    @NotNull(message = "分数不能为空")
    @ApiModelProperty(value = "分数")
    private Integer score;

    @ApiModelProperty(value = "翻倍, 0:否, 1:是")
    private Integer isDoubled;

    @ApiModelProperty(value = "消息弹窗, 0:否, 1:是")
    private Integer isPopups;

    @NotNull(message = "汇报人不能为空")
    @ApiModelProperty(value = "汇报人")
    private Long taskUserId;

    @ApiModelProperty(value = "租户id")
    private Long tenantId;

    @ApiModelProperty(value = "部门id")
    private Long deptId;

    @ApiModelProperty(value = "翻倍上限分数")
    private Integer maxScore;

}
