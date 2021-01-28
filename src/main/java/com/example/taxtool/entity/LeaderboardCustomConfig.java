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
@TableName("t_leaderboard_custom_config")
@ApiModel(value="LeaderboardCustomConfig对象", description="自定义排行榜配置")
public class LeaderboardCustomConfig implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "配置类型1人员分组设置2事件分组设置3排行榜设置")
    private Integer type;

    @ApiModelProperty(value = "子类型1标准2专项3平均分")
    private Integer typeSub;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "租户id")
    private Long tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
