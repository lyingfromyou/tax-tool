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
@TableName("t_fixed_score_items")
@ApiModel(value="FixedScoreItems对象", description="固定分数项目设置")
public class FixedScoreItems implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "状态0停用1启用")
    private Integer status;

    @ApiModelProperty(value = "类型1职务2职称3学历4荣誉5技能6特长7其他")
    private Integer type;

    @ApiModelProperty("项目名称")
    private String name;

    @ApiModelProperty(value = "标准分值(分/日)")
    private Integer scoreDay;

    @ApiModelProperty(value = "备注")
    private String remark;

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

    @ApiModelProperty("固定分人数")
    @TableField(exist = false)
    private Integer userCount;


}
