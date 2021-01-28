package com.example.taxtool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_event_log")
@ApiModel(value="EventLog对象", description="分数管理-事件流程日志")
@Accessors(chain = true)
public class EventLog implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "事件id，对应t_event的id")
    private String eventId;

    @ApiModelProperty(value = "操作用户id")
    private Long operatorUserId;

    @ApiModelProperty(value = "状态，跟随流程状态")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "该审批流程奖励的分数")
    private Integer score;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "操作用户姓名")
    @TableField(exist = false)
    private String operatorUserName;


}
