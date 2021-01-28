package com.example.taxtool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value="TaskReminderLog对象", description="任务提醒日志")
public class TaskReminderLog implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "任务提醒id")
    private Long taskReminderId;

    @ApiModelProperty(value = "任务记录")
    private String content;

    @ApiModelProperty("当前节点操作")
    private String operation;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty("创建人")
    private Long createUser;


}
