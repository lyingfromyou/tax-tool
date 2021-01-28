package com.example.taxtool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("t_event_coupon")
@ApiModel(value="EventCoupon对象", description="事件奖券")
public class EventCoupon implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "奖券分数")
    private Integer couponScore;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "租户id")
    private Long tenantId;

    @ApiModelProperty(value = "0未使用1已使用")
    private Integer status;

    @ApiModelProperty(value = "事件主题id")
    private String eventId;

    @ApiModelProperty(value = "事件详情id")
    private String eventDetailId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
