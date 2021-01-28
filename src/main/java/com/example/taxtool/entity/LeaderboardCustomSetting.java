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

@Data
@Accessors(chain = true)
@TableName("t_leaderboard_custom_setting")
@ApiModel(value="LeaderboardCustomSetting对象", description="排行榜设置")
public class LeaderboardCustomSetting implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "类型1标准2专项3平均分")
    private Integer typeSub;

    @ApiModelProperty(value = "自定义配置排行榜id")
    private Long configId;

    @ApiModelProperty(value = "参与人员分组t_leaderboard_custom_config的id,多个逗号隔开")
    private String participantsUserParam;

    @ApiModelProperty(value = "事件库id")
    private Long eventManagementId;

    @ApiModelProperty(value = "是否可查看所有分组0否1是")
    private Integer isShow;

    @ApiModelProperty("排行榜名称")
    @TableField(exist = false)
    private String configName;
    @ApiModelProperty("事件库名称")
    @TableField(exist = false)
    private String eventManagementName;
    @ApiModelProperty("参与排名的人员分组名称")
    @TableField(exist = false)
    private String participantsUserName;

}
