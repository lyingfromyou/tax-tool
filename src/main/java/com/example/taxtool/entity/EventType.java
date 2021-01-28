package com.example.taxtool.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
@TableName("t_event_type")
@ApiModel(value="EventType对象", description="事件类型")
public class EventType implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "类型名称")
    private String name;

    @ApiModelProperty(value = "上级id")
    private Long parentId;

    @ApiModelProperty(value = "租户id")
    private Long tenantId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "是否已删除, 0-否, 1-是")
    private Integer deleted;

    @ApiModelProperty(value = "子节点列表")
    @TableField(exist = false)
    protected List<EventType> children = new LinkedList<>();

    public void add(EventType node) {
        children.add(node);
    }

}
