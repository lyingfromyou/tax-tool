package com.example.taxtool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel("任务提醒草稿")
public class TaskReminderDraft implements Serializable {

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("创建人")
    private Long createUser;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("元数据")
    private String metadata;

}
