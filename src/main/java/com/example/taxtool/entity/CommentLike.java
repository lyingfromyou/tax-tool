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
@TableName("t_comment_like")
@ApiModel(value="CommentLike对象", description="评论点赞")
public class CommentLike implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "评论id")
    private Long commentId;

    @ApiModelProperty(value = "点赞的用户id")
    private Long createUserId;

    @ApiModelProperty(value = "是否点赞过，0为没有，1为有")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
