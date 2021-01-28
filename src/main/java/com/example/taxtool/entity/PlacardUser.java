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
@TableName("t_placard_user")
@ApiModel(value="PlacardUser对象", description="公告-用户")
public class PlacardUser implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "租户id")
    private Long tenantId;

    @ApiModelProperty(value = "公告id")
    private Long placardId;

    @ApiModelProperty(value = "是否已读0否1是")
    private Integer status;

    @ApiModelProperty(value = "奖励分")
    private Integer scoreAddTotal;

    @ApiModelProperty(value = "扣除分")
    private Integer scoreSubtracTotal;

    @ApiModelProperty(value = "已读时间")
    private Date readTime;

    @ApiModelProperty(value = "已读次数")
    private Integer readCount;

    @ApiModelProperty(value = "是否已删除, 0-否, 1-是")
    private Integer deleted;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "用户姓名")
    @TableField(exist = false)
    private String userName;


}
