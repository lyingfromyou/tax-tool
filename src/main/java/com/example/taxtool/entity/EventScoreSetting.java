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
@TableName("t_event_score_setting")
@ApiModel(value="EventScoreSetting对象", description="奖扣分设置")
public class EventScoreSetting implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "1按人次计算2按事件计算")
    private Integer type;

    @ApiModelProperty(value = "达到多少人或者多少个事件")
    private Integer reached;

    @ApiModelProperty(value = "审核通过后奖分")
    private Integer rewardScore;

    @ApiModelProperty(value = "租户id")
    private Long tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否已删除, 0-否, 1-是")
    private Integer deleted;


}
