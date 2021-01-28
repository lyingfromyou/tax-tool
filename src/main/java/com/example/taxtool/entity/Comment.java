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
@TableName("t_comment")
@ApiModel(value="Comment对象", description="评论")
public class Comment implements Serializable {

    @ApiModelProperty(value = "评论id")
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Long commentId;

    @ApiModelProperty(value = "评论目标id")
    private Long targetId;

    @ApiModelProperty(value = "评论目标类型,  1:商学院")
    private Integer targetType;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "图片, 多张逗号分隔")
    private String images;

    @ApiModelProperty(value = "评论用户id")
    private Long createUserId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
