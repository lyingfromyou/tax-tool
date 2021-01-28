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
@TableName("t_score_detail")
@ApiModel(value="ScoreDetail对象", description="分数详情")
public class ScoreDetail implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "租户id")
    private Long tenantId;

    @ApiModelProperty(value = "+奖分-扣分")
    private String symbol;

    @ApiModelProperty(value = "变化的分")
    private Integer score;

    @ApiModelProperty(value = "分数类型")
    private Integer type;

    @ApiModelProperty(value = "分数来源1日常奖扣2OKR目标3KPI4价值观5奖扣任务6任务提醒7工龄8固定9启动，对应EventTypeEnum枚举")
    private Integer origin;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "其他备注")
    private String extParam;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
