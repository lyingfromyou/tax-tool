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
@TableName("t_reply")
@ApiModel(value="Reply对象", description="回复表")
public class Reply implements Serializable {


    @TableId(value = "reply_id", type = IdType.AUTO)
    private Long replyId;

    @ApiModelProperty(value = "评论id")
    private Long commentId;

    @ApiModelProperty(value = "回复目标id，如果 replyType 是 comment 的话，那么 targetReplyId ＝ commentId，如果 replyType 是 reply 的话，这表示这条回复的父回复。")
    private Long targetReplyId;

    @ApiModelProperty(value = "回复类型, 1:针对评论回复, 2:针对回复的回复")
    private Integer replyType;

    @ApiModelProperty(value = "回复内容")
    private String content;

    @ApiModelProperty(value = "图片, 多张逗号分隔")
    private String images;

    @ApiModelProperty(value = "创建用户")
    private Long createUserId;

    @ApiModelProperty(value = "目标用户id")
    private Long toUserId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty("回复的创建人名称")
    @TableField(exist = false)
    private String createUserName;

    @ApiModelProperty("回复的创建人头像")
    @TableField(exist = false)
    private String createUserHeadUrl;

    @ApiModelProperty("目标用户名称")
    @TableField(exist = false)
    private String toUserName;

    @ApiModelProperty("目标用户头像")
    @TableField(exist = false)
    private String toUserHeadUrl;

    @TableField(exist = false)
    @ApiModelProperty("我是否点赞过,0是没有，1是有")
    private int isMyLike;

    @TableField(exist = false)
    @ApiModelProperty("点赞数")
    private Integer likeCount;
}
