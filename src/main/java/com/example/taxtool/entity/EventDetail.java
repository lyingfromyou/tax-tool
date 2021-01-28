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
import java.util.List;

@Data
@Accessors(chain = true)
@TableName("t_event_detail")
@ApiModel(value="EventDetail对象", description="事件详情")
public class EventDetail implements Serializable {


    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @ApiModelProperty(value = "事件id，对应t_event的id")
    private String eventId;

    @ApiModelProperty(value = "事件类型，事件库id，对应t_event_management的id")
    private Long eventManagementId;

    @ApiModelProperty(value = "事件库的名称")
    private String eventManagementName;

    @ApiModelProperty(value = "描述")
    private String eventDesc;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "事件用户")
    @TableField(exist = false)
    private List<EventUser> eventUsers;

}
