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
@TableName("t_event")
@ApiModel(value="Event对象", description="分数管理-事件")
@Accessors(chain = true)
public class Event implements Serializable {


    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @ApiModelProperty(value = "分数来源1日常奖扣2OKR目标3KPI4价值观5奖扣任务6任务提醒7工龄8固定9启动")
    private Integer origin;

    @ApiModelProperty(value = "类型（来源的小类）")
    private Integer type;

    @ApiModelProperty(value = "事件主题")
    private String eventTopic;

    @ApiModelProperty(value = "事件备注")
    private String eventRemark;

    @ApiModelProperty(value = "奖扣日期")
    private Date eventDate;

    @ApiModelProperty(value = "事件状态0草稿1审核中2被驳回3成功4已撤回5已作废")
    private Integer status;

    @ApiModelProperty(value = "流程状态0草稿1等待审核2审核拒绝3等待复核4复核拒绝5成功6撤回7已作废")
    private Integer eventLogStatus;

    @ApiModelProperty(value = "审核人")
    private Long reviewer;

    @ApiModelProperty(value = "复核人")
    private Long checker;

    @ApiModelProperty(value = "抄送人")
    private Long ccTo;

    @ApiModelProperty(value = "事件数")
    private Integer eventCount;

    @ApiModelProperty(value = "人次")
    private Integer eventUserCount;

    @ApiModelProperty(value = "事件奖励总分")
    private Integer eventScoreAddTotal;

    @ApiModelProperty(value = "事件扣除总分")
    private Integer eventScoreSubtracTotal;

    @ApiModelProperty(value = "租户id")
    private Long tenantId;

    @ApiModelProperty(value = "创建人、发起人")
    private Long createUser;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "删除人")
    private Long deleteUser;

    @ApiModelProperty(value = "删除时间")
    private Date deleteTime;

    @ApiModelProperty(value = "是否已删除, 0-否, 1-是")
    private Integer deleted;

    @ApiModelProperty("内容id, 价值观/KPI等 与type结合使用")
    private String contentId;


    //***************姓名***********************
    @ApiModelProperty(value = "审核人姓名")
    @TableField(exist = false)
    private String reviewerName;

    @ApiModelProperty(value = "复核人姓名")
    @TableField(exist = false)
    private String checkerName;

    @ApiModelProperty(value = "抄送人姓名")
    @TableField(exist = false)
    private String ccToName;

    @ApiModelProperty(value = "创建人、发起人姓名")
    @TableField(exist = false)
    private String createUserName;


}
