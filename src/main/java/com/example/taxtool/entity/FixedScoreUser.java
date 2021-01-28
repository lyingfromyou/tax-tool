package com.example.taxtool.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@TableName("t_fixed_score_user")
@ApiModel(value="FixedScoreUser对象", description="固定分数项目关联人员")
public class FixedScoreUser implements Serializable {


    @ApiModelProperty(value = "项目id")
    private Long itemId;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
