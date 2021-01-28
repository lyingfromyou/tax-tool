package com.example.taxtool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_event_management")
@ApiModel(value="EventManagement对象", description="事件库管理")
public class EventManagement implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "事件类型（t_event_type的id）")
    private Long eventType;

    @ApiModelProperty(value = "事件名称")
    private String eventName;

    @ApiModelProperty(value = "事件描述")
    private String eventDes;

    @ApiModelProperty(value = "分数开始")
    private Integer eventScoreStart;

    @ApiModelProperty(value = "分数结束")
    private Integer eventScoreEnd;

    @ApiModelProperty(value = "是否启用, 0-否, 1-是")
    private Integer status;

    @ApiModelProperty(value = "是否奖券事件，0-否, 1-是")
    private Integer isEventCoupon;

    @ApiModelProperty(value = "是否计件事件，0-否, 1-是")
    private Integer isEventCount;

    @ApiModelProperty(value = "租户id")
    private Long tenantId;

    @ApiModelProperty(value = "创建人")
    private Long createUser;

    @ApiModelProperty(value = "更新人")
    private Long updateUser;

    @ApiModelProperty(value = "删除人")
    private Long deleteUser;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "删除时间")
    private Date deleteTime;

    @ApiModelProperty(value = "是否已删除, 0-否, 1-是")
    private Integer deleted;


    @ApiModelProperty(value = "事件类型名称")
    @TableField(exist = false)
    private String eventTypeName;

}
