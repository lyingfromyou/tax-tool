package com.example.taxtool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_event_score_config")
@ApiModel(value="EventScoreConfig对象", description="事件分数配置")
public class EventScoreConfig implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "基础分")
    private Integer initScore;

    @ApiModelProperty(value = "加入赠送分数")
    private Integer sendScore;

    @ApiModelProperty(value = "成员加入截止日期2020-12")
    private String joinDate;

    @ApiModelProperty(value = "租户id")
    private Long tenantId;

    @ApiModelProperty(value = "创建人")
    private Long createUser;

    @ApiModelProperty(value = "更新人")
    private Long updateUser;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
