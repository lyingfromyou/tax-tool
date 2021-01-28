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
@TableName("t_business_school_course_chapter")
@ApiModel(value="BusinessSchoolCourseChapter对象", description="商学院课程章节")
public class BusinessSchoolCourseChapter implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "课程id")
    private Long courseId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "介绍")
    private String introduction;

    @ApiModelProperty(value = "主视频地址")
    private String videoUrl;

    @ApiModelProperty(value = "视频时长(s)")
    private Integer videoTime;

    @ApiModelProperty(value = "排序")
    private Integer sort;

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
