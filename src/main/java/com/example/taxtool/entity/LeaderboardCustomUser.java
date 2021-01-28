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
@TableName("t_leaderboard_custom_user")
@ApiModel(value="LeaderboardCustomUser对象", description="分组人员")
public class LeaderboardCustomUser implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "自定义配置排行榜id")
    private Long configId;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty("成员编号")
    @TableField(exist = false)
    private String userNo;

    @ApiModelProperty("姓名")
    @TableField(exist = false)
    private String userName;

    @ApiModelProperty("所在部门")
    @TableField(exist = false)
    private String groupName;


}
