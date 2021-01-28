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
@Accessors(chain = true)
@TableName("t_placard")
@ApiModel(value="Placard对象", description="公告")
public class Placard implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "正文")
    private String content;

    @ApiModelProperty(value = "分类1行政2工作3其他")
    private Integer categoryId;

    @ApiModelProperty(value = "阅读范围1全公司2自定义范围")
    private Integer readRange;

    @ApiModelProperty(value = "阅读奖分")
    private Integer score;

    @ApiModelProperty(value = "状态0草稿1已发布2下线")
    private Integer status;

    @ApiModelProperty(value = "开始时间，实时发布就是当前时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间,长期就是2099-12-31 23:59:59")
    private Date endTime;

    @ApiModelProperty(value = "逾期减分")
    private Integer deductionScore;

    @ApiModelProperty(value = "是否弹窗0否1是")
    private Integer isPop;

    @ApiModelProperty(value = "是否置顶0否1是")
    private Integer isTop;

    @ApiModelProperty(value = "弹窗属性-是否弹窗强制阅读0否1是")
    private Integer isPopRead;

    @ApiModelProperty(value = "弹窗属性-弹窗样式123")
    private Integer popStyle;

    @ApiModelProperty(value = "弹窗属性-弹窗次数")
    private Integer popShowCount;

    @ApiModelProperty(value = "弹窗属性-弹窗图片")
    private String popImage;

    @ApiModelProperty(value = "弹窗属性-弹窗正文")
    private String popContent;

    @ApiModelProperty(value = "弹窗属性-开始时间")
    private Date popStartTime;

    @ApiModelProperty(value = "弹窗属性-结束时间")
    private Date popEndTime;

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


    @ApiModelProperty(value = "创建人姓名")
    @TableField(exist = false)
    private String createUserName;
    @ApiModelProperty(value = "已经阅读人数")
    @TableField(exist = false)
    private Integer readCount;
    @ApiModelProperty(value = "是否已读0否1是")
    @TableField(exist = false)
    private Integer readStatus;


}
