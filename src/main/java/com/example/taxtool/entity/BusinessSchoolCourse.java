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
@TableName("t_business_school_course")
@ApiModel(value="BusinessSchoolCourse对象", description="商学院课程")
public class BusinessSchoolCourse implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "课程分类id")
    private Long type;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "作者")
    private String author;

    @ApiModelProperty(value = "评分(几星)")
    private String markStar;

    @ApiModelProperty(value = "主视频地址")
    private String videoUrl;

    @ApiModelProperty(value = "视频总时长(s)")
    private Integer videoTimeTotal;

    @ApiModelProperty(value = "奖励的分数")
    private Integer score;

    @ApiModelProperty(value = "是否有章节0否1是")
    private Integer hasChild;

    @ApiModelProperty(value = "租户id")
    private Long tenantId;

    @ApiModelProperty(value = "创建人")
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

    @ApiModelProperty(value = "用户学习进度")
    @TableField(exist = false)
    private Integer progress = 0;

}
