package com.example.taxtool.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("t_comment_count")
@ApiModel(value="CommentCount对象", description="评论计数")
public class CommentCount implements Serializable {


    private Long targetId;

    private Integer targetType;

    private Integer count;


}
