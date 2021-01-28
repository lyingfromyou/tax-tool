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
@TableName("t_event_user")
@ApiModel(value="EventUser对象", description="分数管理-事件用户")
public class EventUser implements Serializable {


    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @ApiModelProperty(value = "事件id")
    private String eventId;

    @ApiModelProperty(value = "事件详情id，对应t_event_detail的id")
    private String eventDetailId;

    @ApiModelProperty(value = "事件用户id")
    private Long eventUserId;

    @ApiModelProperty(value = "分数")
    private Integer eventScore;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "事件用户姓名")
    @TableField(exist = false)
    private String eventUserName;


}
